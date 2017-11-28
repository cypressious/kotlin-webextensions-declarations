package de.rakhman.webextensions

import com.squareup.kotlinpoet.*
import java.io.File
import java.nio.file.Files

class Generator(val dir: File) {
    fun generate(list: List<Namespace>) {
        generateBrowser(list)
        list.groupBy { it.namespace.substringBefore(".") }.forEach(this::generateMainNamespace)

        Files.walk(dir.toPath())
                .map { it.toFile() }
                .filter { it.extension == "kt" }
                .forEach {
                    it.writeText(it.readText().replace("expect class", "external class"))
                }
    }

    private fun generateBrowser(list: List<Namespace>) {
        FileSpec.builder("", "browser")
                .addType(TypeSpec.expectClassBuilder("Browser")
                        .addProperties(list
                                .filter { "." !in it.namespace }
                                .map {
                                    PropertySpec
                                            .builder(it.namespace, ClassName(it.namespace, it.namespace.capitalize()))
                                            .build()
                                })
                        .build())
                .build().writeTo(dir)

        dir.resolve("browser.kt").appendText("\nexternal val browser: Browser")
    }

    private fun generateMainNamespace(group: Map.Entry<String, List<Namespace>>) {
        val main = group.value.single { it.namespace == group.key }

        val fileBuilder = FileSpec.builder(group.key, group.key)

        val mainTypeBuilder = generateNamespace(main, fileBuilder)

        group.value.filter { it.namespace != group.key }.forEach {
            val name = it.namespace.substringAfter(".")
            mainTypeBuilder.addProperty(PropertySpec.builder(name, ClassName.bestGuess(name.capitalize())).build())

            fileBuilder.addType(generateNamespace(it, fileBuilder).build())
        }

        fileBuilder.addType(mainTypeBuilder.build())

        fileBuilder
                .build()
                .writeTo(dir)
    }

    private fun generateNamespace(ns: Namespace, fileBuilder: FileSpec.Builder): TypeSpec.Builder {
        val name = ns.namespace.substringAfter(".").capitalize()

        val builder = TypeSpec.expectClassBuilder(ClassName.bestGuess(name)).addModifiers(KModifier.EXPECT)
        builder.addFunctions(ns.functions?.flatMap { generateFunctionWithOverloads(it, fileBuilder) } ?: emptyList())

        ns.types?.forEach { generateType(fileBuilder, it) }

        return builder

    }

    private fun generateType(fileBuilder: FileSpec.Builder, type: Type) {
        val name = type.id

        if (type.type in primitiveTypes) {
            fileBuilder.addTypeAlias(
                    TypeAliasSpec.builder(name, ClassName.bestGuess(type.type.capitalize()))
                            .build())
            return
        }

        val properties = type.properties

        val typeBuilder = generateType(name, properties, fileBuilder, true)
        type.description?.let { typeBuilder.addKdoc(it + "\n") }

        fileBuilder.addType(typeBuilder.build())
    }

    private fun generateType(name: String, properties: Map<String, Parameter>?, fileBuilder: FileSpec.Builder, external: Boolean): TypeSpec.Builder {
        val typeBuilder = if (external) TypeSpec.expectClassBuilder(name) else TypeSpec.classBuilder(name)

        typeBuilder
                .addProperties(properties?.entries?.map {
                    PropertySpec
                            .builder(it.key.escapeIfKeyword(), ClassName.bestGuess(parameterTypeName(ParameterContext(it.key, it.value, null, null))))
                            .apply { if (!external) initializer(it.key.escapeIfKeyword()) }
                            .apply { it.value.description?.let { addKdoc(it.replace("%", "%%") + "\n") } }
                            .build()
                } ?: emptyList())

        if (!external) {
            typeBuilder.primaryConstructor(FunSpec.constructorBuilder()
                    .addParameters(
                            properties?.entries?.map {
                                generateParameter(ParameterContext(it.key, it.value, null, fileBuilder)).build()
                            } ?: emptyList()
                    )
                    .build())
        }

        return typeBuilder
    }

    private fun generateFunctionWithOverloads(f: Function, fileBuilder: FileSpec.Builder): List<FunSpec> {
        val parameters = f.parameters
                ?.filter { it.name != f.async }
                ?.takeIf { it.isNotEmpty() }

        val choices = parameters?.getResolvedChoices() ?: listOf(emptyList())

        return choices.mapIndexed { i, list -> generateFunction(f, list, fileBuilder.takeIf { i == 0 }) }
    }

    private fun generateFunction(f: Function, parameters: List<ResolvedChoice>, fileBuilder: FileSpec.Builder?): FunSpec {
        val builder = FunSpec.builder(f.name)

        f.description?.let { builder.addKdoc(it + "\n") }

        parameters.forEach {
            builder.addParameter(generateParameter(ParameterContext(it.name, it.type, f.name, fileBuilder)).build())
        }

        f.deprecated?.let {
            builder.addAnnotation(AnnotationSpec.builder(Deprecated::class).addMember("\"$it\"").build())
        }

        f.async?.let { async ->
            if (async == true) {
                builder.returns("Any".asPromiseType())
                return@let
            }

            val asyncParam = f.parameters!!.first { it.name == async }.parameters?.firstOrNull()
            val parameterType = asyncParam?.let {
                var name = it.name
                if (parameters.any { it.name == name }) name += "Result"

                parameterTypeName(ParameterContext(name, it, f.name, fileBuilder))
            } ?: "Any"

            builder.returns(parameterType.asPromiseType())
        }

        //todo functions mustn't emit bodies

        return builder.build()
    }

    private fun generateParameter(context: ParameterContext): ParameterSpec.Builder {
        return ParameterSpec.builder(context.name.escapeIfKeyword(), ClassName.bestGuess(parameterTypeName(context)))
    }

    private fun parameterTypeName(context: ParameterContext): String {
        val (name, p) = context

        p.`$ref`?.let { return it }

        return when (p.type) {
            "array" -> "Array<${parameterTypeName(context.copy(parameter = p.items!!))}>"
            "integer" -> "Int"
            "string" -> "String"
            "boolean" -> "Boolean"
            "object" -> {
                val prefix = context.functionName?.capitalize() ?: ""
                val typeName = prefix + name.capitalize()

                if (context.fileBuilder != null) {
                    val typeBuilder = generateType(typeName, p.properties, context.fileBuilder, false)
                    context.fileBuilder.addType(typeBuilder.build())
                }

                typeName
            }
            else -> "Any"
        }
    }
}

data class ParameterContext(
        val name: String,
        val parameter: Parameter,
        val functionName: String?,
        val fileBuilder: FileSpec.Builder?
)

private fun String.asPromiseType(): ParameterizedTypeName {
    return ParameterizedTypeName.get(
            ClassName.bestGuess("kotlin.js.Promise"),
            ClassName.bestGuess(this))
}

class ResolvedChoice(val name: String, val type: Parameter)

private fun List<Parameter>.getResolvedChoices(): List<List<ResolvedChoice>> {
    require(size > 0) { "Receiver can't be empty" }

    val param = this[0]

    val heads = if (param.choices != null) {
        param.choices.map { ResolvedChoice(param.name, it) }
    } else {
        listOf(ResolvedChoice(param.name, param))
    }

    if (size == 1) return heads.map { listOf(it) }

    val tails = drop(1).getResolvedChoices()

    return heads.flatMap { head ->
        tails.map { tail ->
            listOf(head) + tail
        }
    }
}

private val primitiveTypes = setOf("string", "int", "boolean")