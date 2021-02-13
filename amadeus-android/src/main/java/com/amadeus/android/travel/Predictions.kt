package com.amadeus.android.travel

import kotlinx.coroutines.CoroutineDispatcher
import retrofit2.Retrofit

class Predictions internal constructor(
    retrofit: Retrofit,
    dispatcher: CoroutineDispatcher
) {

    /**
     * A namespaced client for the
     * `/v1/travel/predictions/trip-purpose` endpoints.
     */
    val tripPurpose = TripPurpose(retrofit, dispatcher)

    /**
     * A namespaced client for the
     * `/v1/travel/predictions/flight-delay` endpoints.
     */
    val flightDelay = FlightDelay(retrofit, dispatcher)

}
