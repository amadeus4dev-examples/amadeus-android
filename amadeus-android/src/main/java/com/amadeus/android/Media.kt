package com.amadeus.android

import com.amadeus.android.media.Files
import kotlinx.coroutines.CoroutineDispatcher
import okhttp3.OkHttpClient

class Media internal constructor(
    baseUrl: String,
    httpClient: OkHttpClient,
    dispatcher: CoroutineDispatcher
) {

    /**
     * A namespaced client for the
     * `/v2/media/files` endpoints.
     */
    val files = Files(baseUrl, httpClient, dispatcher)

}