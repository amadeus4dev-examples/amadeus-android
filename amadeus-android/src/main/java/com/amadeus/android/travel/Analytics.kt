package com.amadeus.android.travel

import com.squareup.moshi.Moshi
import kotlinx.coroutines.CoroutineDispatcher
import okhttp3.OkHttpClient


class Analytics internal constructor(
    baseUrl: String,
    httpClient: OkHttpClient,
    moshi: Moshi,
    dispatcher: CoroutineDispatcher
) {

    /**
     * A namespaced client for the
     * `/v1/travel/analytics/air-traffic` endpoints.
     */
    val airTraffic = AirTraffic(baseUrl, httpClient, moshi, dispatcher)

}