package com.amadeus.android.media

import com.squareup.moshi.Moshi
import kotlinx.coroutines.CoroutineDispatcher
import okhttp3.OkHttpClient

class Files internal constructor(
    baseUrl: String,
    httpClient: OkHttpClient,
    moshi: Moshi,
    dispatcher: CoroutineDispatcher
) {

    /**
     * A namespaced client for the
     * `/v2/media/files/generated-photos` endpoints.
     */
    val generatedPhotos = GeneratedPhotos(baseUrl, httpClient, moshi, dispatcher)

}