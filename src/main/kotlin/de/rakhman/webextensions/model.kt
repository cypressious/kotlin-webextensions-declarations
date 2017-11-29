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
        val actual: Boolean
)

data class Function(
        val name: String,
        val type: String,
        val description: String?,
        val async: Any?,
        val parameters: List<Parameter>?,
        val deprecated: String?,
        val unsupported: Boolean
)

data class Parameter(
        val type: String?,
        val name: String?,
        val description: String?,
        val optional: Boolean,
        val properties: Map<String, Parameter>?,
        val deprecated: String?,
        val `$ref`: String?,
        val parameters: List<Parameter>?,
        val items: Parameter?,
        val choices: List<Parameter>?,
        val unsupported: Boolean
)

class Event(

)
