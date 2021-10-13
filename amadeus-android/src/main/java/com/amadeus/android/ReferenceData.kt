package com.amadeus.android

import com.amadeus.android.referenceData.*
import kotlinx.coroutines.CoroutineDispatcher
import retrofit2.Retrofit

class ReferenceData internal constructor(
    private val retrofit: Retrofit,
    private val dispatcher: CoroutineDispatcher
) {
    /**
     * A namespaced client for the
     * `/v2/reference-data/urls` endpoints.
     */
    val urls = Urls(retrofit, dispatcher)

    /**
     * A namespaced client for the
     * `/v1/reference-data/locations` and
     * `/v2/reference-data/locations` endpoints.
     */
    val locations = Locations(retrofit, dispatcher)

    /**
     * A namespaced client for the
     * `/v1/reference-data/airlines` endpoints.
     */
    val airlines = Airlines(retrofit, dispatcher)

    /**
     * A namespaced client for the
     * `/v1/reference-data/recommended-locations` endpoints.
     */
    val recommendedLocations = RecommendedLocations(retrofit, dispatcher)

    /**
     * A namespaced client for the
     * `/v1/reference-data/location/:id` endpoints.
     */
    fun location(id: String) = Location(retrofit, dispatcher, id)
}