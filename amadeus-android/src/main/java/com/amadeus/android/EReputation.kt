package com.amadeus.android

import com.amadeus.android.ereputation.HotelSentiments
import kotlinx.coroutines.CoroutineDispatcher
import retrofit2.Retrofit

class EReputation internal constructor(
    retrofit: Retrofit,
    dispatcher: CoroutineDispatcher
) {

    /**
     * A namespaced client for the
     * `/v2/e-reputation/hotel-sentiments` endpoints.
     */
    val hotelSentiments = HotelSentiments(retrofit, dispatcher)

}