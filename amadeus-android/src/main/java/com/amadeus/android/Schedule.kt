package com.amadeus.android

import com.amadeus.android.schedule.Flights
import com.squareup.moshi.Moshi
import kotlinx.coroutines.CoroutineDispatcher
import okhttp3.OkHttpClient

class Schedule internal constructor(
    baseUrl: String,
    httpClient: OkHttpClient,
    moshi: Moshi,
    dispatcher: CoroutineDispatcher
) {

    /**
     * A namespaced client for the
     * `/v2/schedule/flights` endpoints.
     */
    val flights = Flights(baseUrl, httpClient, moshi, dispatcher)

}
