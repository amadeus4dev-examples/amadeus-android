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
    retrofit: Retrofit,
    dispatcher: CoroutineDispatcher
) : BaseApi(dispatcher) {

    private val api: ItineraryPriceMetricsApi = retrofit.create()

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
