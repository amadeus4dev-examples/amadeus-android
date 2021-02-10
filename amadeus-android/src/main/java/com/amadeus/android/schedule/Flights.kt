package com.amadeus.android.schedule

import com.amadeus.android.BaseApi
import com.amadeus.android.domain.air.apis.ScheduleApi
import com.amadeus.android.tools.GeneratedCodeConverters
import com.squareup.moshi.Moshi
import kotlinx.coroutines.CoroutineDispatcher
import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.create

class Flights internal constructor(
    baseUrl: String,
    httpClient: OkHttpClient,
    moshi: Moshi,
    dispatcher: CoroutineDispatcher
) : BaseApi(moshi, dispatcher) {

    override val basePath = "v2/"

    private val api: ScheduleApi = Retrofit.Builder()
        .baseUrl(baseUrl + basePath)
        .addConverterFactory(GeneratedCodeConverters.converterFactory(moshi))
        .client(httpClient)
        .build()
        .create()

    suspend fun get(
        carrierCode: String,
        flightNumber: String,
        scheduleDepartureDate: String,
        operationalSuffix: String? = null
    ) = safeApiCall {
        api.getFlightStatus(
            carrierCode,
            flightNumber,
            scheduleDepartureDate,
            operationalSuffix
        )
    }

}