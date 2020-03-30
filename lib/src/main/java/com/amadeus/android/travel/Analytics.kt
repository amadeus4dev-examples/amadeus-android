package com.amadeus.android.travel

import kotlinx.coroutines.CoroutineDispatcher
import okhttp3.OkHttpClient


class Analytics internal constructor(
    baseUrl: String,
    httpClient: OkHttpClient,
    dispatcher: CoroutineDispatcher
) {

    /**
     * A namespaced client for the
     * `/v1/travel/analytics/air-traffic` endpoints.
     */
    val airTraffic = AirTraffic(baseUrl, httpClient, dispatcher)

}