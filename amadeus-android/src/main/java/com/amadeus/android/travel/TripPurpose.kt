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
    retrofit: Retrofit,
    dispatcher: CoroutineDispatcher
) : BaseApi(dispatcher) {

    private val api: TripPurposePredictionApi = retrofit.create()

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