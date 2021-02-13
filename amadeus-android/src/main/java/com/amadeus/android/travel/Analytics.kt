package com.amadeus.android.travel

import kotlinx.coroutines.CoroutineDispatcher
import retrofit2.Retrofit


class Analytics internal constructor(
    retrofit: Retrofit,
    dispatcher: CoroutineDispatcher
) {

    /**
     * A namespaced client for the
     * `/v1/travel/analytics/air-traffic` endpoints.
     */
    val airTraffic = AirTraffic(retrofit, dispatcher)

}