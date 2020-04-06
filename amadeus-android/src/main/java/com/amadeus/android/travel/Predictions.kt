package com.amadeus.android.travel

import kotlinx.coroutines.CoroutineDispatcher
import okhttp3.OkHttpClient

class Predictions internal constructor(
    baseUrl: String,
    httpClient: OkHttpClient,
    dispatcher: CoroutineDispatcher
) {

    /**
     * A namespaced client for the
     * `/v1/travel/predictions/trip-purpose` endpoints.
     */
    val tripPurpose = TripPurpose(baseUrl, httpClient, dispatcher)

    /**
     * A namespaced client for the
     * `/v1/travel/predictions/flight-delay` endpoints.
     */
    val flightDelay = FlightDelay(baseUrl, httpClient, dispatcher)

}