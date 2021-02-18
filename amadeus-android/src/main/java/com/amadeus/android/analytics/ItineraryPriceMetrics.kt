package com.amadeus.android.analytics

import com.amadeus.android.BaseApi
import com.amadeus.android.domain.air.apis.ItineraryPriceMetricsApi
import com.amadeus.android.tools.GeneratedCodeConverters
import com.squareup.moshi.Moshi
import kotlinx.coroutines.CoroutineDispatcher
import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.create

class ItineraryPriceMetrics internal constructor(
    baseUrl: String,
    httpClient: OkHttpClient,
    moshi: Moshi,
    dispatcher: CoroutineDispatcher
) : BaseApi(moshi, dispatcher) {

    override val basePath = "v1/"

    private val api: ItineraryPriceMetricsApi = Retrofit.Builder()
        .baseUrl(baseUrl + basePath)
        .addConverterFactory(GeneratedCodeConverters.converterFactory(moshi))
        .client(httpClient)
        .build()
        .create()

    suspend fun get(
        originIataCode: String,
        destinationIataCode: String,
        departureDate: String,
        currencyCode: String? = null,
        oneWay: Boolean? = null
        ) = safeApiCall {
            api.getItineraryPriceMetrics(
                originIataCode,
                destinationIataCode,
                departureDate,
                currencyCode,
                oneWay
            )
        }

}
