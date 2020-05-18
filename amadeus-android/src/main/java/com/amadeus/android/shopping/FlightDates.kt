package com.amadeus.android.shopping

import com.amadeus.android.BaseApi
import com.amadeus.android.domain.air.apis.FlightDatesApi
import com.amadeus.android.tools.GeneratedCodeConverters
import com.squareup.moshi.Moshi
import kotlinx.coroutines.CoroutineDispatcher
import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.create

class FlightDates internal constructor(
    baseUrl: String,
    httpClient: OkHttpClient,
    moshi: Moshi,
    dispatcher: CoroutineDispatcher
) : BaseApi(moshi, dispatcher) {

    override val basePath = "v1/"

    private val api: FlightDatesApi = Retrofit.Builder()
        .baseUrl(baseUrl + basePath)
        .addConverterFactory(GeneratedCodeConverters.converterFactory(moshi))
        .client(httpClient)
        .build()
        .create()

    suspend fun get(
        origin: String,
        destination: String,
        departureDate: String? = null,
        oneWay: Boolean? = null,
        duration: String? = null,
        nonStop: Boolean? = null,
        maxPrice: Long? = null,
        viewBy: String? = null
    ) = safeApiCall {
        api.getFlightDates(
            origin,
            destination,
            departureDate,
            oneWay,
            duration,
            nonStop,
            maxPrice,
            viewBy
        )
    }

}