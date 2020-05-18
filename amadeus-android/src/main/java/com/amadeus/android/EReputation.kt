package com.amadeus.android

import com.amadeus.android.ereputation.HotelSentiments
import com.squareup.moshi.Moshi
import kotlinx.coroutines.CoroutineDispatcher
import okhttp3.OkHttpClient

class EReputation internal constructor(
    baseUrl: String,
    httpClient: OkHttpClient,
    moshi: Moshi,
    dispatcher: CoroutineDispatcher
) {

    /**
     * A namespaced client for the
     * `/v2/e-reputation/hotel-sentiments` endpoints.
     */
    val hotelSentiments = HotelSentiments(baseUrl, httpClient, moshi, dispatcher)

}