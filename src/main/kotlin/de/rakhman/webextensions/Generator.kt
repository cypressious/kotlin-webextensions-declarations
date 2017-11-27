package de.rakhman.webextensions

import com.squareup.kotlinpoet.*
import java.io.File

class Generator(val dir: File) {
    fun generate(list: List<Namespace>) {
        generateBrowser(list)
        list.groupBy { it.namespace.substringBefore(".") }.forEach(this::generateMainNamespace)
    }

    private fun generateBrowser(list: List<Namespace>) {
        FileSpec.builder("", "browser")
                .addType(TypeSpec.classBuilder("Browser")
                        .addModifiers(KModifier.EXTERNAL)
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

        val builder = TypeSpec.classBuilder(ClassName.bestGuess(name)).addModifiers(KModifier.EXTERNAL)
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
        val typeBuilder = TypeSpec.classBuilder(name)
                .addProperties(properties?.entries?.map {
                    PropertySpec
                            .builder(it.key, ClassName.bestGuess(parameterTypeName(it.key, it.value, null)))
                            .apply { if (!external) initializer(it.key) }
                            .apply { it.value.description?.let { addKdoc(it.replace("%", "%%") + "\n") } }
                            .build()
                } ?: emptyList())

        if (external) {
            typeBuilder.addModifiers(KModifier.EXTERNAL)
        } else {
            typeBuilder.primaryConstructor(FunSpec.constructorBuilder()
                    .addParameters(
                            properties?.entries?.map {
                                generateParameter(it.key, it.value, fileBuilder).build()
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
            builder.addParameter(generateParameter(it.name, it.type, fileBuilder).build())
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
            val parameterType = asyncParam?.let { parameterTypeName(it.name, it, fileBuilder) } ?: "Any"
            builder.returns(parameterType.asPromiseType())
        }

        //todo functions mustn't emit bodies

        return builder.build()
    }

    private fun generateParameter(name: String, parameter: Parameter, fileBuilder: FileSpec.Builder?): ParameterSpec.Builder {
        return ParameterSpec.builder(name, ClassName.bestGuess(parameterTypeName(name, parameter, fileBuilder)))
    }

    private fun parameterTypeName(name: String, p: Parameter, fileBuilder: FileSpec.Builder?): String {
        p.`$ref`?.let { return it }

        return when (p.type) {
            "array" -> "Array<${parameterTypeName(name, p.items!!, fileBuilder)}>"
            "integer" -> "Int"
            "string" -> "String"
            "boolean" -> "Boolean"
            "object" -> {
                if (fileBuilder != null) {
                    val typeBuilder = generateType(name.capitalize(), p.properties, fileBuilder, false)
                    fileBuilder.addType(typeBuilder.build())
                }

                name.capitalize()
            }
            else -> "Any"
        }
    }
}

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