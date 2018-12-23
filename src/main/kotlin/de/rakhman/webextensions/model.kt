package de.rakhman.webextensions

data class Namespace(
        val namespace: String,
        val description: String?,
        val types: List<Type>?,
        val properties: Map<String, Parameter>?,
        val functions: List<Function>?,
        val events: List<Event>?
)

data class Type(
        val id: String?,
        val type: String?,
        val properties: Map<String, Parameter>?,
        val description: String?,
        val `$extend`: String?,
        val choices: List<Any>?,
        val actual: Boolean,
        val items: Parameter?,
        val additionalProperties: Parameter?,
        val patternProperties: Map<String, Parameter>?,
        val functions: List<Function>?,
        val isExternal: Boolean = false
)

data class Function(
        val name: String,
        val type: String,
        val description: String?,
        val async: Any?,
        val parameters: List<Parameter>?,
        val deprecated: String?,
        val unsupported: Boolean,
        val returns: Parameter?,
        val patternProperties: Map<String, Parameter>?
)

data class Parameter(
        val type: String? = null,
        val name: String? = null,
        val description: String? = null,
        val optional: Boolean = false,
        val properties: Map<String, Parameter>? = null,
        val deprecated: String? = null,
        val `$ref`: String? = null,
        val parameters: List<Parameter>? = null,
        val items: Parameter? = null,
        val choices: List<Parameter>? = null,
        val unsupported: Boolean = false,
        val additionalProperties: Parameter? = null,
        val patternProperties: Map<String, Parameter>? = null,
        val value: Any? = null
)

data class Event(
        val name: String,
        val unsupported: Boolean,
        val deprecated: String?,
        val type: String?,
        val description: String?,
        val parameters: List<Parameter>?
)
