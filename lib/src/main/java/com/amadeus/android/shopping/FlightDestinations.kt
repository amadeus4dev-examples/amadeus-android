package com.amadeus.android.shopping

import com.amadeus.android.base.BaseApi
import com.amadeus.android.domain.air.apis.FlightDestinationsApi
import com.amadeus.android.domain.air.tools.GeneratedCodeConverters
import kotlinx.coroutines.CoroutineDispatcher
import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.create

class FlightDestinations internal constructor(
    baseUrl: String,
    httpClient: OkHttpClient,
    dispatcher: CoroutineDispatcher
) : BaseApi(dispatcher) {

    override val basePath = "v1/"

    private val api: FlightDestinationsApi = Retrofit.Builder()
        .baseUrl(baseUrl + basePath)
        .addConverterFactory(GeneratedCodeConverters.converterFactory())
        .client(httpClient)
        .build()
        .create()

    suspend fun get(
        origin: String,
        departureDate: String? = null,
        oneWay: Boolean? = null,
        duration: String? = null,
        nonStop: Boolean? = null,
        maxPrice: Long? = null,
        viewBy: String? = null
    ) = safeApiCall {
        api.getFlightDestinations(
            origin,
            departureDate,
            oneWay,
            duration,
            nonStop,
            maxPrice,
            viewBy
        )
    }

}