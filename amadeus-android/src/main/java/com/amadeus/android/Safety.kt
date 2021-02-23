package com.amadeus.android

import com.amadeus.android.safety.SafetyRatedLocation
import com.amadeus.android.safety.SafetyRatedLocations
import com.squareup.moshi.Moshi
import kotlinx.coroutines.CoroutineDispatcher
import okhttp3.OkHttpClient

class Safety internal constructor(
    private val baseUrl: String,
    private val httpClient: OkHttpClient,
    private val moshi: Moshi,
    private val dispatcher: CoroutineDispatcher
) {

    /**
     * A namespaced client for the
     * `/v1/safety/safety-rated-locations` endpoints.
     */
    val safetyRatedLocations = SafetyRatedLocations(baseUrl, httpClient, moshi, dispatcher)

    /**
     * A namespaced client for the
     * `/v1/safety/safety-rated-locations/:id` endpoints.
     */
    fun safetyRatedLocation(safePlaceId: String) = SafetyRatedLocation(baseUrl, httpClient, moshi, dispatcher, safePlaceId)
}
