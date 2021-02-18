package com.amadeus.android

import com.amadeus.android.analytics.ItineraryPriceMetrics
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
     * `/v1/analytics/itinerary-price-metrics` endpoints.
     */
    val itineraryPriceMetrics = ItineraryPriceMetrics(baseUrl, httpClient, moshi, dispatcher)

}
