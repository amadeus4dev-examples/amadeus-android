package com.amadeus.android.travel

import com.squareup.moshi.Moshi
import kotlinx.coroutines.CoroutineDispatcher
import okhttp3.OkHttpClient

class AirTraffic internal constructor(
    baseUrl: String,
    httpClient: OkHttpClient,
    moshi: Moshi,
    dispatcher: CoroutineDispatcher
) {

    /**
     * A namespaced client for the
     * `/v1/travel/analytics/air-traffic/traveled` endpoints.
     */
    var traveled = Traveled(baseUrl, httpClient, moshi, dispatcher)

    /**
     * A namespaced client for the
     * `/v1/travel/analytics/air-traffic/booked` endpoints.
     */
    var booked = Booked(baseUrl, httpClient, moshi, dispatcher)

    /**
     * A namespaced client for the
     * `/v1/travel/analytics/air-traffic/busiest-period` endpoints.
     */
    var busiestPeriod = BusiestPeriod(baseUrl, httpClient, moshi, dispatcher)

}