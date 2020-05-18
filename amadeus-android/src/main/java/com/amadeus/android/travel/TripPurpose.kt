package com.amadeus.android.travel

import com.amadeus.android.BaseApi
import com.amadeus.android.domain.trip.apis.TripPurposePredictionApi
import com.amadeus.android.tools.GeneratedCodeConverters
import com.squareup.moshi.Moshi
import kotlinx.coroutines.CoroutineDispatcher
import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.create

class TripPurpose internal constructor(
    baseUrl: String,
    httpClient: OkHttpClient,
    moshi: Moshi,
    dispatcher: CoroutineDispatcher
) : BaseApi(moshi, dispatcher) {

    override val basePath = "v1/"

    private val api: TripPurposePredictionApi = Retrofit.Builder()
        .baseUrl(baseUrl + basePath)
        .addConverterFactory(GeneratedCodeConverters.converterFactory(moshi))
        .client(httpClient)
        .build()
        .create()

    suspend fun get(
        originLocationCode: String,
        destinationLocationCode: String,
        departureDate: String,
        returnDate: String,
        searchDate: String? = null
    ) = safeApiCall {
        api.getTripPurposePrediction(
            originLocationCode,
            destinationLocationCode,
            departureDate,
            returnDate,
            searchDate
        )
    }

}