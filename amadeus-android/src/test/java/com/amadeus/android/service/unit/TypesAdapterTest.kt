package com.amadeus.android.service.unit

import com.amadeus.android.tools.NumbersAdapter
import com.amadeus.android.tools.TypesAdapterFactory
import com.amadeus.android.tools.XNullableAdapterFactory
import com.squareup.moshi.Moshi
import com.squareup.moshi.Types
import org.junit.Before
import org.junit.Test

class TypesAdapterTest {

    private lateinit var moshi: Moshi

    @Before
    fun setup() {
        moshi = Moshi.Builder()
            .add(NumbersAdapter.FACTORY)
            .add(XNullableAdapterFactory())
            .add(TypesAdapterFactory())
            .build()
    }

    @Test
    fun `Adapter factory to convert int, long, float and double string values to objects`() {
        val input = "[${Int.MAX_VALUE}, ${Long.MAX_VALUE}, ${Float.MAX_VALUE}, ${Double.MAX_VALUE}]"
        val type = Types.newParameterizedType(
            List::class.java,
            Any::class.java
        )
        val result = moshi.adapter<List<Any>>(type).fromJson(input)
        assert(result != null)
        if (result != null) {
            assert(result[0] is Int)
            assert(result[1] is Long)
            assert(result[2] is Double)
            assert(result[3] is Double)
        }
    }

}