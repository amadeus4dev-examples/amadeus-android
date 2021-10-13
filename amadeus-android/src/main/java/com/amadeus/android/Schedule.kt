package com.amadeus.android

import com.amadeus.android.schedule.Flights
import kotlinx.coroutines.CoroutineDispatcher
import retrofit2.Retrofit

class Schedule internal constructor(
    retrofit: Retrofit,
    dispatcher: CoroutineDispatcher
) {

    /**
     * A namespaced client for the
     * `/v2/schedule/flights` endpoints.
     */
    val flights = Flights(retrofit, dispatcher)

}
