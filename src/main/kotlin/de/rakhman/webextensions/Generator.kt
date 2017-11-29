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
                                .map { it.namespace }
                                .filter { "." !in it }
                                .distinct()
                                .map {
                                    PropertySpec
                                            .builder(it, ClassName(it, it.nameSpaceName()))
                                            .build()
                                })
                        .build())
                .build().writeTo(dir)

        dir.resolve("browser.kt").appendText("\nexternal val browser: Browser")
    }

    private fun generateMainNamespace(group: Map.Entry<String, List<Namespace>>) {
        val mains = group.value.filter { it.namespace == group.key }

        val fileBuilder = FileSpec.builder(group.key, group.key)

        val mainTypeBuilder = generateNamespace(mains, fileBuilder)

        group.value.filter { it.namespace != group.key }.forEach {
            val name = it.namespace.substringAfter(".")
            mainTypeBuilder.addProperty(PropertySpec.builder(name, ClassName.bestGuess(name.nameSpaceName())).build())

            fileBuilder.addType(generateNamespace(listOf(it), fileBuilder).build())
        }

        fileBuilder.addType(mainTypeBuilder.build())

        fileBuilder
                .build()
                .writeTo(dir)
    }

    private fun generateNamespace(namespaceParts: List<Namespace>, fileBuilder: FileSpec.Builder): TypeSpec.Builder {
        val ns = merge(namespaceParts)

        val name = ns.namespace.substringAfter(".").nameSpaceName()

        val functions = ns.functions?.filter { !it.unsupported } ?: emptyList()

        val builder = TypeSpec.expectClassBuilder(ClassName.bestGuess(name)).addModifiers(KModifier.EXPECT)
        builder.addFunctions(functions.flatMap { generateFunctionWithOverloads(it, fileBuilder) })

        ns.types?.forEach { generateType(fileBuilder, it) }

        return builder

    }

    private fun String.nameSpaceName() = capitalize() + "Namespace"


    private fun generateType(fileBuilder: FileSpec.Builder, type: Type) {
        val name = type.id!!

        if (type.type in primitiveTypes) {
            fileBuilder.addTypeAlias(
                    TypeAliasSpec.builder(name, ClassName.bestGuess(type.type!!.capitalize())).build())
            return
        }

        if (type.properties == null) {
            fileBuilder.addTypeAlias(
                    TypeAliasSpec.builder(name, Any::class).build())
            return
        }

        val typeBuilder = generateType(name, type.properties, fileBuilder, true)
        type.description?.let { typeBuilder.addKdoc(it + "\n") }

        fileBuilder.addType(typeBuilder.build())
    }

    private fun generateType(name: String, properties: Map<String, Parameter>, fileBuilder: FileSpec.Builder, external: Boolean): TypeSpec.Builder {
        val typeBuilder = if (external) TypeSpec.expectClassBuilder(name) else TypeSpec.classBuilder(name)

        val props = properties.entries.filter { !it.value.unsupported }

        typeBuilder
                .addProperties(props.map {
                    PropertySpec
                            .builder(it.key.escapeIfKeyword(), ClassName.bestGuess(parameterTypeName(ParameterContext(it.key, it.value, null, fileBuilder))))
                            .apply { if (!external) initializer(it.key.escapeIfKeyword()) }
                            .apply { it.value.description?.let { addKdoc(it.replace("%", "%%") + "\n") } }
                            .build()
                })

        if (!external) {
            typeBuilder.primaryConstructor(FunSpec.constructorBuilder()
                    .addParameters(
                            props.map {
                                generateParameter(ParameterContext(it.key, it.value, null, null)).build()
                            }
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

        // generate all object types once to prevent duplicates
        choices.flatMap { it }.distinct().forEach { parameterTypeName(ParameterContext(it.name, it.type, f.name, fileBuilder)) }
        returnTypeName(f, fileBuilder)

        return choices.map { list -> generateFunction(f, list) }
    }

    private fun generateFunction(f: Function, parameters: List<ResolvedChoice>): FunSpec {
        val builder = FunSpec.builder(f.name)

        f.description?.let { builder.addKdoc(it + "\n") }

        parameters.forEach {
            builder.addParameter(generateParameter(ParameterContext(it.name, it.type, f.name)).build())
        }

        f.deprecated?.let {
            builder.addAnnotation(AnnotationSpec.builder(Deprecated::class).addMember("\"$it\"").build())
        }

        val returnType = returnTypeName(f, null)
        returnType?.let { builder.returns(returnType.asPromiseType()) }

        return builder.build()
    }

    private fun generateParameter(context: ParameterContext): ParameterSpec.Builder {
        return ParameterSpec.builder(context.name.escapeIfKeyword(), ClassName.bestGuess(parameterTypeName(context)))
    }

    private fun parameterTypeName(context: ParameterContext): String {
        val p = context.parameter

        p.`$ref`?.let {
            return if (it == "Promise") "Promise<Any?>" else it
        }

        val suffix = if (p.optional) "?" else ""

        return when (p.type) {
            "array" -> "Array<${parameterTypeName(context.copy(parameter = p.items!!))}>"
            "integer" -> "Int"
            "string" -> "String"
            "boolean" -> "Boolean"
            "object" -> {
                val prefix = context.functionName?.capitalize() ?: ""
                val typeName = prefix + context.name.capitalize()

                if (context.fileBuilder != null) {
                    if (p.properties != null) {
                        val typeBuilder = generateType(typeName, p.properties, context.fileBuilder, context.external)
                        context.fileBuilder.addType(typeBuilder.build())
                    } else {
                        context.fileBuilder.addTypeAlias(TypeAliasSpec.builder(typeName, Any::class).build())
                    }
                }

                typeName
            }
            else -> "Any"
        } + suffix
    }

    private fun returnTypeName(f: Function, fileBuilder: FileSpec.Builder?): String? {
        return when (f.async) {
            true -> "Any"
            is String -> {
                val asyncParam = f.parameters!!.first { it.name == f.async }.parameters?.firstOrNull()
                asyncParam?.let {
                    var name = it.name!!
                    if (f.parameters.any { it.name == name }) name += "Result"

                    parameterTypeName(ParameterContext(name, it, f.name, fileBuilder, true))
                } ?: "Any"
            }
            else -> null
        }
    }
}

data class ParameterContext(
        val name: String,
        val parameter: Parameter,
        val functionName: String?,
        val fileBuilder: FileSpec.Builder? = null,
        val external: Boolean = false
)

private fun String.asPromiseType(): ParameterizedTypeName {
    return ParameterizedTypeName.get(
            ClassName.bestGuess("kotlin.js.Promise"),
            ClassName.bestGuess(this))
}

data class ResolvedChoice(val name: String, val type: Parameter)

private fun List<Parameter>.getResolvedChoices(): List<List<ResolvedChoice>> {
    require(size > 0) { "Receiver can't be empty" }

    val param = this[0]

    val heads = if (param.choices != null) {
        param.choices.map { ResolvedChoice(param.name!!, it) }
    } else {
        listOf(ResolvedChoice(param.name!!, param))
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