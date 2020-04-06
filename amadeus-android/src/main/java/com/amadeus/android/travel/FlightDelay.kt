package com.amadeus.android.travel

import com.amadeus.android.base.BaseApi
import com.amadeus.android.domain.air.apis.FlightDelayPredictionApi
import com.amadeus.android.domain.air.tools.GeneratedCodeConverters
import kotlinx.coroutines.CoroutineDispatcher
import okhttp3.OkHttpClient
import org.threeten.bp.LocalDate
import org.threeten.bp.ZonedDateTime
import retrofit2.Retrofit
import retrofit2.create

class FlightDelay internal constructor(
    baseUrl: String,
    httpClient: OkHttpClient,
    dispatcher: CoroutineDispatcher
) : BaseApi(dispatcher) {

    override val basePath = "v1/"

    private val api: FlightDelayPredictionApi = Retrofit.Builder()
        .baseUrl(baseUrl + basePath)
        .addConverterFactory(GeneratedCodeConverters.converterFactory())
        .client(httpClient)
        .build()
        .create()

    suspend fun get(
        originLocationCode: String,
        destinationLocationCode: String,
        departureDate: LocalDate,
        departureTime: ZonedDateTime,
        arrivalDate: LocalDate,
        arrivalTime: ZonedDateTime,
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