package com.amadeus.android

import com.amadeus.android.airport.Predictions
import com.squareup.moshi.Moshi
import kotlinx.coroutines.CoroutineDispatcher
import okhttp3.OkHttpClient

class Airport internal constructor(
    baseUrl: String,
    httpClient: OkHttpClient,
    moshi: Moshi,
    dispatcher: CoroutineDispatcher
) {

    /**
     * A namespaced client for the
     * `/v1/airport/predictions` endpoints.
     */
    val predictions = Predictions(baseUrl, httpClient, moshi, dispatcher)

}