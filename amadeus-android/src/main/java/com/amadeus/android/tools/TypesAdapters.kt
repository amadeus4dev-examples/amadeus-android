package com.amadeus.android.tools

import com.squareup.moshi.JsonAdapter
import com.squareup.moshi.JsonReader
import com.squareup.moshi.JsonWriter
import com.squareup.moshi.Moshi
import com.squareup.moshi.internal.Util
import java.io.IOException
import java.lang.reflect.Type
import java.math.BigDecimal

/**
 * Moshi Factory to handle all the custom types we want to support,
 * such as [BigDecimal].
 */
class TypesAdapterFactory : JsonAdapter.Factory {
    private val types = mapOf<Type, JsonAdapter<*>>(
        BigDecimal::class.java to BigDecimalJsonAdapter()
    )

    override fun create(
        type: Type,
        annotations: MutableSet<out Annotation>,
        moshi: Moshi
    ): JsonAdapter<*>? {
        if (annotations.isEmpty()) {
            for (targetType in types.keys) {
                if (Util.typesMatch(type, targetType)) return types[targetType]
            }
        }
        return null
    }
}

/**
 * Util Abstract [JsonAdapter] to support Parsing of `null` values for types annotated
 * with [XNullable]. This adapter will check if the next token on the JSON reader is a `null` and
 * return it. Otherwise will invoke the `fromNonNullString` abstract function.
 */
internal abstract class XNullableJsonAdapter<T> : JsonAdapter<T>() {

    abstract fun fromNonNullString(nextString: String): T

    override fun fromJson(reader: JsonReader): T? {
        return if (reader.peek() != JsonReader.Token.NULL) {
            fromNonNullString(reader.nextString())
        } else {
            reader.nextNull<Any>()
            null
        }
    }
}

internal class BigDecimalJsonAdapter : XNullableJsonAdapter<BigDecimal>() {
    override fun fromNonNullString(nextString: String) = BigDecimal(nextString)

    override fun toJson(writer: JsonWriter, value: BigDecimal?) {
        value?.let { writer.value(it) }
    }
}

internal class NumbersAdapter(
    private val delegate: JsonAdapter<Any>
) : JsonAdapter<Any?>() {

    @Throws(IOException::class)
    override fun fromJson(reader: JsonReader): Any? {
        if (reader.peek() != JsonReader.Token.NUMBER) {
            return delegate.fromJson(reader)
        }
        val decimal = BigDecimal(reader.nextString())
        return if (decimal.scale() > 0) {
            decimal.toDouble()
        } else {
            try {
                decimal.intValueExact()
            } catch (e1: ArithmeticException) {
                try {
                    decimal.longValueExact()
                } catch (e2: ArithmeticException) {
                    decimal.toDouble()
                }
            }
        }
    }

    @Throws(IOException::class)
    override fun toJson(writer: JsonWriter, value: Any?) {
        delegate.toJson(writer, value)
    }

    companion object {
        val FACTORY: Factory = object : Factory {
            override fun create(
                type: Type,
                annotations: Set<Annotation?>,
                moshi: Moshi
            ): JsonAdapter<*>? {
                if (type !== Any::class.java) return null
                if (annotations.isNotEmpty()) return null
                val delegate = moshi.nextAdapter<Any>(this, Any::class.java, annotations)
                return NumbersAdapter(delegate)
            }
        }
    }
}
