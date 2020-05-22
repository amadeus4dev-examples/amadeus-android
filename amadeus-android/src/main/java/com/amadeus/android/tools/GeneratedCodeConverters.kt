package com.amadeus.android.tools

import com.squareup.moshi.Moshi
import retrofit2.Converter
import retrofit2.converter.moshi.MoshiConverterFactory

object GeneratedCodeConverters {

    @JvmStatic
    fun converterFactory(moshi: Moshi): Converter.Factory {
        return WrapperConverterFactory(
            CollectionFormatConverterFactory(),
            EnumToValueConverterFactory(),
            MoshiConverterFactory.create(moshi)
        )
    }
}
