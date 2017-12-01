package de.rakhman.webextensions

fun merge(list: List<Namespace>): Namespace {
    require(list.isNotEmpty())

    val types = mergeTypes(list.flatMap { it.types ?: emptyList() })

    val properties = list
            .flatMap { it.properties?.entries ?: emptySet() }
            .associate { it.key to it.value.resolve(it.key, types, false) }
            .takeUnless { it.isEmpty() }

    val functions = list
            .flatMap { it.functions?.map { it.resolve(types) } ?: emptyList() }
            .takeUnless { it.isEmpty() }

    return Namespace(
            namespace = list.first().namespace,
            description = list.mapNotNull { it.description }.firstOrNull(),
            types = types.values.toList().takeUnless { it.isEmpty() },
            properties = properties,
            functions = functions,
            events = list.flatMap { it.events ?: emptyList() }.takeUnless { it.isEmpty() }
    )
}

private fun mergeTypes(types: List<Type>): MutableMap<String?, Type> {
    val result = types
            .groupingBy { it.id ?: it.`$extend` }
            .reduceTo(mutableMapOf()) { key, left, right ->
                Type(
                        id = key,
                        description = left.description ?: right.description,
                        type = left.type ?: right.type,
                        properties = left.properties.orEmpty() + right.properties.orEmpty(),
                        choices = left.choices.orEmpty() + right.choices.orEmpty(),
                        `$extend` = null,
                        actual = false,
                        items = left.items ?: right.items
                )
            }

    for ((key, value) in result.toMap()) {
        if (key == null) continue

        result[key] = value.copy(
                properties = value.properties?.entries?.associate { it.key to it.value.resolve(it.key, result, false) },
                items = value.items?.resolve(key, result, false)
        )
    }

    return result
}

private fun Function.resolve(types: MutableMap<String?, Type>): Function {
    return copy(parameters = parameters?.map { it.resolve(it.name!!, types, it.name != async, it.name == async) })
}

private fun Parameter.resolve(name: String, types: MutableMap<String?, Type>, actual: Boolean, isReturn: Boolean = false): Parameter {
    if (type == "array") return copy(items = items?.resolve(name, types, actual))
    if (type != "object" && choices == null && parameters == null) return this

    var typeName = name.capitalize()
    var counter = 1

    while (types.containsKey(typeName)) typeName = name.capitalize() + ++counter

    val choices = choices?.map { it.resolve(name, types, actual) }

    if (!isReturn) {
        types[typeName] = Type(
                id = typeName,
                type = null,
                properties = properties?.map { it.key to it.value.resolve(it.key, types, actual) }?.toMap(),
                description = description,
                `$extend` = null,
                choices = null,
                actual = actual,
                items = null
        )
    }

    return copy(
            type = null,
            properties = null,
            `$ref` = typeName,
            parameters = parameters?.map { it.resolve(it.name!!, types, actual) },
            choices = choices
    )
}