package com.amadeus.android.travel

import kotlinx.coroutines.CoroutineDispatcher
import okhttp3.OkHttpClient

class AirTraffic internal constructor(
    baseUrl: String,
    httpClient: OkHttpClient,
    dispatcher: CoroutineDispatcher
) {

    /**
     * A namespaced client for the
     * `/v1/travel/analytics/air-traffic/searched` endpoints.
     */
    var searched = Searched(baseUrl, httpClient, dispatcher)

    /**
     * A namespaced client for the
     * `/v1/travel/analytics/air-traffic/searched/by-destination` endpoints.
     */
    var searchedByDestination = SearchedByDestination(baseUrl, httpClient, dispatcher)

    /**
     * A namespaced client for the
     * `/v1/travel/analytics/air-traffic/traveled` endpoints.
     */
    var traveled = Traveled(baseUrl, httpClient, dispatcher)

    /**
     * A namespaced client for the
     * `/v1/travel/analytics/air-traffic/booked` endpoints.
     */
    var booked = Booked(baseUrl, httpClient, dispatcher)

    /**
     * A namespaced client for the
     * `/v1/travel/analytics/air-traffic/busiest-period` endpoints.
     */
    var busiestPeriod = BusiestPeriod(baseUrl, httpClient, dispatcher)

}