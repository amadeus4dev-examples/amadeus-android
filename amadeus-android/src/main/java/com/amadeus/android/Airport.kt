package com.amadeus.android

import com.amadeus.android.airport.Predictions
import kotlinx.coroutines.CoroutineDispatcher
import retrofit2.Retrofit

class Airport internal constructor(
    retrofit: Retrofit,
    dispatcher: CoroutineDispatcher
) {

    /**
     * A namespaced client for the
     * `/v1/airport/predictions` endpoints.
     */
    val predictions = Predictions(retrofit, dispatcher)

}