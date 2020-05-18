package com.amadeus.android.travel

import com.amadeus.android.BaseApi
import com.amadeus.android.domain.air.apis.FlightDelayPredictionApi
import com.amadeus.android.tools.GeneratedCodeConverters
import com.squareup.moshi.Moshi
import kotlinx.coroutines.CoroutineDispatcher
import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.create

class FlightDelay internal constructor(
    baseUrl: String,
    httpClient: OkHttpClient,
    moshi: Moshi,
    dispatcher: CoroutineDispatcher
) : BaseApi(moshi, dispatcher) {

    override val basePath = "v1/"

    private val api: FlightDelayPredictionApi = Retrofit.Builder()
        .baseUrl(baseUrl + basePath)
        .addConverterFactory(GeneratedCodeConverters.converterFactory(moshi))
        .client(httpClient)
        .build()
        .create()

    suspend fun get(
        originLocationCode: String,
        destinationLocationCode: String,
        departureDate: String,
        departureTime: String,
        arrivalDate: String,
        arrivalTime: String,
        aircraftCode: String,
        carrierCode: String,
        flightNumber: String,
        duration: String
    ) = safeApiCall {
        api.getFlightDelayPrediction(
            originLocationCode,
            destinationLocationCode,
            departureDate,
            departureTime,
            arrivalDate,
            arrivalTime,
            aircraftCode,
            carrierCode,
            flightNumber,
            duration
        )
    }

}