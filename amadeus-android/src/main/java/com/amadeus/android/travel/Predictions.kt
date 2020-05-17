package com.amadeus.android.travel

import com.squareup.moshi.Moshi
import kotlinx.coroutines.CoroutineDispatcher
import okhttp3.OkHttpClient

class Predictions internal constructor(
    baseUrl: String,
    httpClient: OkHttpClient,
    moshi: Moshi,
    dispatcher: CoroutineDispatcher
) {

    /**
     * A namespaced client for the
     * `/v1/travel/predictions/trip-purpose` endpoints.
     */
    val tripPurpose = TripPurpose(baseUrl, httpClient, moshi, dispatcher)

    /**
     * A namespaced client for the
     * `/v1/travel/predictions/flight-delay` endpoints.
     */
    val flightDelay = FlightDelay(baseUrl, httpClient, moshi, dispatcher)

}
