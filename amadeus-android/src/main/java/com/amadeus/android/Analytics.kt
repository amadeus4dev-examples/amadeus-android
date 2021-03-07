package com.amadeus.android

import com.amadeus.android.analytics.ItineraryPriceMetrics
import com.squareup.moshi.Moshi
import kotlinx.coroutines.CoroutineDispatcher
import okhttp3.OkHttpClient
import retrofit2.Retrofit

class Analytics internal constructor(
    retrofit: Retrofit,
    dispatcher: CoroutineDispatcher
) {

    /**
     * A namespaced client for the
     * `/v1/analytics/itinerary-price-metrics` endpoints.
     */
    val itineraryPriceMetrics = ItineraryPriceMetrics(retrofit, dispatcher)

}
