package com.amadeus.android

import com.amadeus.android.ereputation.HotelSentiments
import kotlinx.coroutines.CoroutineDispatcher
import okhttp3.OkHttpClient

class EReputation internal constructor(
    baseUrl: String,
    httpClient: OkHttpClient,
    dispatcher: CoroutineDispatcher
) {

    /**
     * A namespaced client for the
     * `/v2/e-reputation/hotel-sentiments` endpoints.
     */
    val hotelSentiments = HotelSentiments(baseUrl, httpClient, dispatcher)

}