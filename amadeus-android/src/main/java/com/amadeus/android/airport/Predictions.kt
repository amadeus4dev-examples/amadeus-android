package com.amadeus.android.airport

import kotlinx.coroutines.CoroutineDispatcher
import retrofit2.Retrofit

class Predictions internal constructor(
    retrofit: Retrofit,
    dispatcher: CoroutineDispatcher
) {

    /**
     * A namespaced client for the
     * `/v1/airport/predictions/on-time` endpoints.
     */
    val onTime = OnTime(retrofit, dispatcher)

}