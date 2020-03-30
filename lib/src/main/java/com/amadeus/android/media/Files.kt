package com.amadeus.android.media

import kotlinx.coroutines.CoroutineDispatcher
import okhttp3.OkHttpClient

class Files internal constructor(
    baseUrl: String,
    httpClient: OkHttpClient,
    dispatcher: CoroutineDispatcher
) {

    /**
     * A namespaced client for the
     * `/v2/media/files/generated-photos` endpoints.
     */
    val generatedPhotos = GeneratedPhotos(baseUrl, httpClient, dispatcher)

}