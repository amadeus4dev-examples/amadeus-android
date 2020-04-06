package com.amadeus.android.referenceData

import kotlinx.coroutines.CoroutineDispatcher
import okhttp3.OkHttpClient

class Urls internal constructor(
    baseUrl: String,
    httpClient: OkHttpClient,
    dispatcher: CoroutineDispatcher
) {

    /**
     * A namespaced client for the
     * `/v2/reference-data/urls/checkin-links` endpoints.
     */
    val checkinLinks = CheckinLinks(baseUrl, httpClient, dispatcher)

}