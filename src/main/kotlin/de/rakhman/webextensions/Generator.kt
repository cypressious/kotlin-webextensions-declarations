package de.rakhman.webextensions

import com.squareup.kotlinpoet.*
import com.squareup.kotlinpoet.ParameterizedTypeName.Companion.parameterizedBy
import java.io.File


class Generator(val dir: File) {
    fun generate(list: List<Namespace>) {
        generateBrowser(list)
        list.groupBy { it.namespace.substringBefore(".") }.forEach(this::generateMainNamespace)
    }

    private fun generateBrowser(list: List<Namespace>) {
        FileSpec.builder("webextensions", "browser")
            .addType(TypeSpec.classBuilder("Browser")
                .addModifiers(KModifier.EXTERNAL)
                .addProperties(list
                    .map { it.namespace }
                    .filter { "." !in it }
                    .distinct()
                    .map {
                        PropertySpec
                            .builder(it, ClassName(it, it.nameSpaceName()))
                            .build()
                    })
                .build()
            )
            .addType(
                TypeSpec.classBuilder("Event")
                    .addModifiers(KModifier.EXTERNAL)
                    .addTypeVariable(TypeVariableName("T", variance = KModifier.IN))
                    .addFunction(
                        FunSpec
                            .builder("addListener")
                            .addParameter(
                                ParameterSpec.builder(
                                    "listener",
                                    TypeVariableName("T")
                                ).build()
                            )
                            .build()
                    )
                    .addFunction(
                        FunSpec
                            .builder("removeListener")
                            .addParameter(
                                ParameterSpec.builder(
                                    "listener",
                                    TypeVariableName("T")
                                ).build()
                            )
                            .build()
                    )
                    .addFunction(
                        FunSpec
                            .builder("hasListener")
                            .addParameter(
                                ParameterSpec.builder(
                                    "listener",
                                    TypeVariableName("T")
                                ).build()
                            )
                            .returns(ClassName.bestGuess("Boolean"))
                            .build()
                    )
                    .build()
            )
            .addProperty(
                PropertySpec
                    .builder("browser", ClassName.bestGuess("Browser"))
                    .addModifiers(KModifier.EXTERNAL)
                    .build()
            )
            .build().writeTo(dir)
    }

    private fun generateMainNamespace(group: Map.Entry<String, List<Namespace>>) {
        val mains = group.value.filter { it.namespace == group.key }

        val fileBuilder = FileSpec.builder(group.key, group.key)

        val mainTypeBuilder = generateNamespace(merge(mains), fileBuilder)

        group.value.filter { it.namespace != group.key }.forEach {
            val name = it.namespace.substringAfter(".")
            mainTypeBuilder.addProperty(
                PropertySpec.builder(
                    name,
                    ClassName.bestGuess(name.nameSpaceName())
                ).build()
            )

            fileBuilder.addType(generateNamespace(merge(listOf(it)), fileBuilder).build())
        }

        fileBuilder.addType(mainTypeBuilder.build())

        fileBuilder
            .build()
            .writeTo(dir)
    }

    private fun generateNamespace(ns: Namespace, fileBuilder: FileSpec.Builder): TypeSpec.Builder {
        ns.types?.forEach { generateType(it, fileBuilder) }

        val name = ns.namespace.substringAfter(".").nameSpaceName()
        val functions = ns.functions?.filterNot { it.unsupported } ?: emptyList()
        val events = ns.events?.filterNot { it.unsupported } ?: emptyList()

        return TypeSpec.classBuilder(ClassName.bestGuess(name))
            .addModifiers(KModifier.EXTERNAL)
            .addFunctions(functions.flatMap { generateFunctionWithOverloads(it) })
            .addProperties(events.map { generateEvent(it) })
    }

    private fun generateEvent(event: Event): PropertySpec {
        val type = ClassName.bestGuess("webextensions.Event")
            .parameterizedBy(
                LambdaTypeName.get(
                    returnType = ClassName.bestGuess("Unit"),
                    parameters = event.parameters?.map {
                        generateParameter(
                            it.name!!,
                            it,
                            false
                        ).build()
                    } ?: emptyList()
                )
            )

        return PropertySpec.builder(event.name, type).build()
    }

    private fun generateType(type: Type, fileBuilder: FileSpec.Builder) {
        val name = type.id!!

        when (type.type) {
            "string", "int", "boolean", "any" -> {
                fileBuilder.addTypeAlias(TypeAliasSpec
                    .builder(name, ClassName.bestGuess(type.type.capitalize()))
                    .apply { type.description?.let { addKdoc(it.replace("%", "%%")) } }
                    .build())
                return
            }
            "array" -> {
                fileBuilder.addTypeAlias(TypeAliasSpec
                    .builder(
                        name, ClassName.bestGuess("Array").parameterizedBy(
                            parameterType("", type.items!!)
                        )
                    )
                    .apply { type.description?.let { addKdoc(it.replace("%", "%%")) } }
                    .build())
                return
            }
        }

        val additionalProperties = type.additionalProperties
        val patternProperties = type.patternProperties

        if (type.properties == null && additionalProperties == null && patternProperties == null) {
            require(type.type == "object" || type.type == null) {
                "Unxpected type $type"
            }

            fileBuilder.addTypeAlias(TypeAliasSpec
                .builder(name, ClassName.bestGuess("Any"))
                .apply { type.description?.let { addKdoc(it.replace("%", "%%")) } }
                .build())
            return
        }

        val properties = (type.properties ?: emptyMap()).entries.filter { !it.value.unsupported }

        val typeBuilder = TypeSpec.classBuilder(name)
            .addProperties(properties.map {
                PropertySpec
                    .varBuilder(it.key, parameterType(it.key, it.value))
                    .apply { it.value.description?.let { addKdoc(it.replace("%", "%%") + "\n") } }
                    .initializer(it.key)
                    .build()
            })
            .primaryConstructor(FunSpec.constructorBuilder()
                .addParameters(
                    properties.map {
                        ParameterSpec
                            .builder(it.key, parameterType(it.key, it.value))
                            .apply { if (it.value.optional) defaultValue("null") }
                            .build()
                    }
                )
                .build())

        generateIndexers(additionalProperties, patternProperties, typeBuilder)

        type.description?.let { typeBuilder.addKdoc(it + "\n") }

        fileBuilder.addType(typeBuilder.build())
    }

    private fun generateIndexers(
        additionalProperties: Parameter?,
        patternProperties: Map<String, Parameter>?,
        typeBuilder: TypeSpec.Builder
    ) {
        val types = mutableListOf<Parameter>()

        additionalProperties?.flattenTypeTo(types)
        patternProperties?.forEach { _, value -> value.flattenTypeTo(types) }

        val count = types.size
        when (count) {
            0 -> return
            1 -> {
                val type = types.single()

                generateGetter(type, typeBuilder)
                generateSetter(type, typeBuilder)
            }

            else -> {
                types.forEach { generateSetter(it, typeBuilder) }
                generateGetter(Parameter(type = "any"), typeBuilder)
            }
        }

        typeBuilder.addAnnotation(
            AnnotationSpec.builder(Suppress::class)
                .addMember("\"NOTHING_TO_INLINE\", \"UnsafeCastFromDynamic\"")
                .build()
        )
    }

    private fun Parameter.flattenTypeTo(types: MutableList<Parameter>) {
        if (choices != null) {
            choices.forEach { it.flattenTypeTo(types) }
        } else {
            types += this
        }
    }


    private fun generateGetter(type: Parameter, typeBuilder: TypeSpec.Builder) {
        typeBuilder.addFunction(
            FunSpec.builder("get")
                .addModifiers(KModifier.OPERATOR, KModifier.INLINE)
                .addParameter("key", ClassName.bestGuess("String"))
                .addCode("return asDynamic()[key]")
                .returns(parameterType("", type))
                .build()
        )
    }

    private fun generateSetter(type: Parameter, typeBuilder: TypeSpec.Builder) {
        typeBuilder.addFunction(
            FunSpec.builder("set")
                .addModifiers(KModifier.OPERATOR, KModifier.INLINE)
                .addParameter("key", ClassName.bestGuess("String"))
                .addParameter("value", parameterType("", type))
                .addCode("asDynamic()[key] = value\n")
                .build()
        )
    }

    private fun generateFunctionWithOverloads(f: Function): List<FunSpec> {
        val parameters = f.parameters
            ?.filter { it.name != f.async }
            ?.takeIf { it.isNotEmpty() }

        val choices = parameters?.getResolvedChoices() ?: listOf(emptyList())

        return choices.map { list -> generateFunction(f, list) }
    }

    private fun generateFunction(f: Function, parameters: List<Parameter>): FunSpec {
        val builder = FunSpec.builder(f.name)

        f.description?.let { builder.addKdoc(it + "\n") }

        parameters.forEach {
            builder.addParameter(generateParameter(it.name!!, it).build())
        }

        f.deprecated?.let {
            builder.addAnnotation(AnnotationSpec.builder(Deprecated::class).addMember("\"$it\"").build())
        }

        returnType(f)?.let { builder.returns(it) }

        return builder.build()
    }

    private fun generateParameter(
        name: String,
        parameter: Parameter,
        allowDefault: Boolean = true
    ): ParameterSpec.Builder {
        return ParameterSpec.builder(name, parameterType(name, parameter))
            .apply {
                if (allowDefault && parameter.optional) {
                    defaultValue("definedExternally")
                }
            }
    }

    private fun returnType(f: Function): TypeName? {
        f.returns?.let {
            return parameterType("", it)
        }

        val returnTypeName = when (f.async) {
            true -> ClassName.bestGuess("Any")
            is String -> {
                val asyncParam =
                    f.parameters!!.first { it.name == f.async }.parameters?.firstOrNull()
                asyncParam?.let {
                    parameterType("", it)
                } ?: ClassName.bestGuess("Any")
            }
            else -> null
        }

        return returnTypeName?.let {
            ClassName.bestGuess("kotlin.js.Promise").parameterizedBy(it)
        }
    }


    private fun parameterType(name: String, parameter: Parameter): TypeName {
        val type = when (parameter.type) {
            "function" -> {
                //special case for Post.postMessage
                if (name == "postMessage") {
                    LambdaTypeName.get(
                        parameters = *arrayOf(ClassName.bestGuess("Any")),
                        returnType = ClassName.bestGuess("Unit")
                    )
                } else {
                    LambdaTypeName.get(returnType = ClassName.bestGuess("Unit"))
                }
            }

            "array" -> ClassName.bestGuess("Array").parameterizedBy(
                parameterType("", parameter.items!!)
            )

            "any" -> return ClassName("", "dynamic")

            else -> {
                ClassName.bestGuess(parameterTypeName(parameter))

            }
        }

        return if (parameter.optional) {
            type.asNullable()
        } else {
            type
        }

    }

    private fun parameterTypeName(p: Parameter): String {
        p.`$ref`?.let {
            return when (it) {
                "Promise" -> "Promise<Any?>"
                "UnrecognizedProperty" -> "manifest.UnrecognizedProperty"
                else -> it
            }
        }

        return when (p.type) {
            "integer" -> "Int"
            "number" -> "Float"
            "string" -> "String"
            "boolean" -> "Boolean"
            "any" -> "dynamic"
            else -> throw IllegalArgumentException("Connot decide name for $p")
        }
    }

}

private fun List<Parameter>.getResolvedChoices(): List<List<Parameter>> {
    require(size > 0) { "Receiver can't be empty" }

    val param = this[0]

    val heads = if (param.choices != null) {
        param.choices.map { it.copy(name = param.name) }
    } else {
        listOf(param)
    }

    if (size == 1) return heads.map { listOf(it) }

    val tails = drop(1).getResolvedChoices()

    return heads.flatMap { head ->
        tails.map { tail ->
            listOf(head) + tail
        }
    }
}

private fun String.nameSpaceName() = capitalize() + "Namespace"
