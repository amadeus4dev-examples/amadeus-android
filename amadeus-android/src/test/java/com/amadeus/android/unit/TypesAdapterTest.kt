package com.amadeus.android.unit

import com.amadeus.android.Amadeus
import com.squareup.moshi.Moshi
import com.squareup.moshi.Types
import org.junit.Before
import org.junit.Test

class TypesAdapterTest {

    private lateinit var moshi: Moshi

    @Before
    fun setup() {
        val amadeus = Amadeus.Builder()
            .setClientId("1234")
            .setClientSecret("1234")
            .build()
        moshi = amadeus.moshi
    }

    @Test
    @Suppress("SENSELESS_COMPARISON")
    fun `Adapter factory to convert int, long, float and double string values to objects`() {
        val input = "[" +
                "${Int.MAX_VALUE}," +
                "${Long.MAX_VALUE}," +
                "${Float.MAX_VALUE}," +
                "${Double.MAX_VALUE}," +
                "\"${Int.MAX_VALUE}\"," +
                "${null}" +
                "]"
        val type = Types.newParameterizedType(
            List::class.java,
            Any::class.java
        )
        val result = moshi.adapter<List<Any>>(type).fromJson(input)
        assert(result != null)
        if (result != null) {
            assert(result[0] is Int)
            assert(result[1] is Long)
            // Always return double and never float
            assert(result[2] is Double)
            assert(result[3] is Double)
            assert(result[4] is String)
            assert(result[5] == null)
        }
    }

}