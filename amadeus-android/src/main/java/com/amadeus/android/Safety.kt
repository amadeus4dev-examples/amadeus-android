package com.amadeus.android

import com.amadeus.android.safety.SafetyRatedLocation
import com.amadeus.android.safety.SafetyRatedLocations
import com.squareup.moshi.Moshi
import kotlinx.coroutines.CoroutineDispatcher
import okhttp3.OkHttpClient
import retrofit2.Retrofit

class Safety internal constructor(
    private val retrofit: Retrofit,
    private val dispatcher: CoroutineDispatcher
) {

    /**
     * A namespaced client for the
     * `/v1/safety/safety-rated-locations` endpoints.
     */
    val safetyRatedLocations = SafetyRatedLocations(retrofit, dispatcher)

    /**
     * A namespaced client for the
     * `/v1/safety/safety-rated-locations/:id` endpoints.
     */
    fun safetyRatedLocation(safePlaceId: String) = SafetyRatedLocation(retrofit, dispatcher, safePlaceId)
}
