package com.amadeus.android.airport

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
     * `/v1/airport/predictions/on-time` endpoints.
     */
    val onTime = OnTime(baseUrl, httpClient, moshi, dispatcher)

}