package de.rakhman.webextensions.serialization

import com.google.gson.Gson
import com.google.gson.TypeAdapter
import com.google.gson.TypeAdapterFactory
import com.google.gson.reflect.TypeToken
import com.google.gson.stream.JsonReader
import com.google.gson.stream.JsonToken
import com.google.gson.stream.JsonWriter
import de.rakhman.webextensions.Parameter


class ParameterAdapterFactory : TypeAdapterFactory {

    @Suppress("UNCHECKED_CAST")
    override fun <T> create(gson: Gson, type: TypeToken<T>): TypeAdapter<T>? {
        if (type.rawType != Parameter::class.java) return null

        val defaultAdapter = gson.getDelegateAdapter(this, type) as TypeAdapter<Parameter>
        return ParameterAdapter(defaultAdapter) as TypeAdapter<T>
    }

    class ParameterAdapter(
            private val defaultAdapter: TypeAdapter<Parameter>
    ) : TypeAdapter<Parameter>() {

        override fun write(out: JsonWriter, value: Parameter) {
            defaultAdapter.write(out, value)
        }

        override fun read(`in`: JsonReader): Parameter? {
            if (`in`.peek() != JsonToken.BEGIN_OBJECT) {
                `in`.skipValue()
                return null
            }
            return defaultAdapter.read(`in`)
        }
    }
}