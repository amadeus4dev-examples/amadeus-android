package com.amadeus.android.travel

import kotlinx.coroutines.CoroutineDispatcher
import retrofit2.Retrofit

class AirTraffic internal constructor(
    retrofit: Retrofit,
    dispatcher: CoroutineDispatcher
) {

    /**
     * A namespaced client for the
     * `/v1/travel/analytics/air-traffic/traveled` endpoints.
     */
    var traveled = Traveled(retrofit, dispatcher)

    /**
     * A namespaced client for the
     * `/v1/travel/analytics/air-traffic/booked` endpoints.
     */
    var booked = Booked(retrofit, dispatcher)

    /**
     * A namespaced client for the
     * `/v1/travel/analytics/air-traffic/busiest-period` endpoints.
     */
    var busiestPeriod = BusiestPeriod(retrofit, dispatcher)

}