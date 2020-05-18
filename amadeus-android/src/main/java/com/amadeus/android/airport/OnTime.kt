package com.amadeus.android.airport

import com.amadeus.android.BaseApi
import com.amadeus.android.domain.air.apis.AirportOntimePredictionApi
import com.amadeus.android.tools.GeneratedCodeConverters
import com.squareup.moshi.Moshi
import kotlinx.coroutines.CoroutineDispatcher
import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.create

class OnTime internal constructor(
    baseUrl: String,
    httpClient: OkHttpClient,
    moshi: Moshi,
    dispatcher: CoroutineDispatcher
) : BaseApi(moshi, dispatcher) {

    override val basePath = "v1/"

    private val api: AirportOntimePredictionApi = Retrofit.Builder()
        .baseUrl(baseUrl + basePath)
        .addConverterFactory(GeneratedCodeConverters.converterFactory(moshi))
        .client(httpClient)
        .build()
        .create()

    suspend fun get(
        airportCode: String,
        date: String
    ) = safeApiCall {
        api.getAirportOnTimePrediction(
            airportCode,
            date
        )
    }

}