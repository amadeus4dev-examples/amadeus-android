package com.amadeus.android.referenceData

import com.squareup.moshi.Moshi
import kotlinx.coroutines.CoroutineDispatcher
import okhttp3.OkHttpClient

class Urls internal constructor(
    baseUrl: String,
    httpClient: OkHttpClient,
    moshi: Moshi,
    dispatcher: CoroutineDispatcher
) {

    /**
     * A namespaced client for the
     * `/v2/reference-data/urls/checkin-links` endpoints.
     */
    val checkinLinks = CheckinLinks(baseUrl, httpClient, moshi, dispatcher)

}