package com.amadeus.android

import com.amadeus.android.airport.Predictions
import kotlinx.coroutines.CoroutineDispatcher
import okhttp3.OkHttpClient

class Airport internal constructor(
    baseUrl: String,
    httpClient: OkHttpClient,
    dispatcher: CoroutineDispatcher
) {

    /**
     * A namespaced client for the
     * `/v1/airport/predictions` endpoints.
     */
    val predictions = Predictions(baseUrl, httpClient, dispatcher)

}