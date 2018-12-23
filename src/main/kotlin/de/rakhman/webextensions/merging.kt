package de.rakhman.webextensions

fun merge(list: List<Namespace>): Namespace {
    require(list.isNotEmpty())

    val types = mergeTypes(list.flatMap { it.types ?: emptyList() })

    val properties = list
        .flatMap { it.properties?.entries ?: emptySet() }
        .associate { it.key to it.value.resolve(it.key, types) }
        .takeUnless { it.isEmpty() }

    val functions = list
        .flatMap { it.functions?.map { it.resolve(types) } ?: emptyList() }
        .takeUnless { it.isEmpty() }

    val events = list
        .flatMap { it.events?.map { it.resolve(types) } ?: emptyList() }
        .takeUnless { it.isEmpty() }

    return Namespace(
        namespace = list.first().namespace,
        description = list.mapNotNull { it.description }.firstOrNull(),
        types = types.values.toList().takeUnless { it.isEmpty() },
        properties = properties,
        functions = functions,
        events = events
    )
}

private fun mergeTypes(types: List<Type>): MutableMap<String?, Type> {
    val result = mutableMapOf<String?, Type>()

    result.putAll(types
        .map { it.copy(functions = it.functions?.map { it.resolve(result) }) }
        .groupingBy { it.id ?: it.`$extend` }
        .reduceTo(mutableMapOf()) { key, left, right ->
            Type(
                id = key,
                description = left.description ?: right.description,
                type = left.type ?: right.type,
                properties = (left.properties.orEmpty() + right.properties.orEmpty()).takeUnless { it.isEmpty() },
                choices = (left.choices.orEmpty() + right.choices.orEmpty()).takeUnless { it.isEmpty() },
                `$extend` = null,
                actual = false,
                items = left.items ?: right.items,
                additionalProperties = left.additionalProperties ?: right.additionalProperties,
                patternProperties = (left.patternProperties.orEmpty() + right.patternProperties.orEmpty()).takeUnless { it.isEmpty() },
                functions = (left.functions.orEmpty() + right.functions.orEmpty()).takeUnless { it.isEmpty() }
            )
        })

    for ((key, value) in result.toMap()) {
        if (key == null) continue

        result[key] = value.copy(
            properties = value.properties?.entries?.associate {
                it.key to it.value.resolve(
                    it.key,
                    result
                )
            },
            items = value.items?.resolve(key, result)
        )
    }

    return result
}

private fun Event.resolve(types: MutableMap<String?, Type>): Event {
    return copy(
        parameters = parameters?.map { it.resolve(it.name!!, types) }
    )
}

private fun Function.resolve(types: MutableMap<String?, Type>): Function {
    return copy(
        parameters = parameters?.map {
            it.resolve(
                it.name!!,
                types,
                it.name != async,
                it.name == async
            )
        },
        returns = returns?.resolve(name + "Result", types)
    )
}

private fun Parameter.resolve(
    name: String,
    types: MutableMap<String?, Type>,
    actual: Boolean = false,
    isReturn: Boolean = false
): Parameter {
    if (type == "function") return copy(parameters = parameters?.map {
        it.resolve(
            it.name ?: name,
            types
        )
    })
    if (type == "array") return copy(items = items?.resolve(name, types, actual))
    if (type != "object" &&
        choices == null &&
        parameters == null &&
        additionalProperties == null &&
        patternProperties == null
    ) {
        return this
    }

    val parameters = parameters?.map { it.resolve(it.name!!, types, actual) }
    val choices = choices?.map { it.resolve(name, types, actual) }
    val additionalProperties = additionalProperties?.resolve(name, types)
    val patternProperties = patternProperties?.mapValues { it.value.resolve(name, types) }

    var typeName = name.capitalize()
    var counter = 1

    while (types.containsKey(typeName)) typeName = name.capitalize() + ++counter

    if (!isReturn) {
        val previous = types.put(
            typeName, Type(
                id = typeName,
                type = null,
                properties = properties?.map {
                    it.key to it.value.resolve(
                        it.key,
                        types,
                        actual
                    )
                }?.toMap(),
                description = description,
                `$extend` = null,
                choices = null,
                actual = actual,
                items = null,
                additionalProperties = additionalProperties,
                patternProperties = patternProperties,
                functions = null
            )
        )

        require(previous == null) {
            "A type with the name $typeName already existed"
        }
    }

    return copy(
        type = null,
        properties = null,
        `$ref` = typeName,
        parameters = parameters,
        choices = choices,
        additionalProperties = null,
        patternProperties = null
    )
}