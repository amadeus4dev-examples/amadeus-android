package com.amadeus.android

import com.amadeus.android.referenceData.Airlines
import com.amadeus.android.referenceData.Location
import com.amadeus.android.referenceData.Locations
import com.amadeus.android.referenceData.Urls
import kotlinx.coroutines.CoroutineDispatcher
import okhttp3.OkHttpClient

class ReferenceData internal constructor(
    private val baseUrl: String,
    private val httpClient: OkHttpClient,
    private val dispatcher: CoroutineDispatcher
) {
    /**
     * A namespaced client for the
     * `/v2/reference-data/urls` endpoints.
     */
    val urls = Urls(baseUrl, httpClient, dispatcher)

    /**
     * A namespaced client for the
     * `/v1/reference-data/locations` and
     * `/v2/reference-data/locations` endpoints.
     */
    val locations = Locations(baseUrl, httpClient, dispatcher)

    /**
     * A namespaced client for the
     * `/v1/reference-data/airlines` endpoints.
     */
    val airlines = Airlines(baseUrl, httpClient, dispatcher)

    /**
     * A namespaced client for the
     * `/v1/reference-data/location/:id` endpoints.
     */
    fun location(id: String) = Location(baseUrl, httpClient, dispatcher, id)
}