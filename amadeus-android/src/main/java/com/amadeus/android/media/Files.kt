package com.amadeus.android.media

import kotlinx.coroutines.CoroutineDispatcher
import retrofit2.Retrofit

class Files internal constructor(
    retrofit: Retrofit,
    dispatcher: CoroutineDispatcher
) {

    /**
     * A namespaced client for the
     * `/v2/media/files/generated-photos` endpoints.
     */
    val generatedPhotos = GeneratedPhotos(retrofit, dispatcher)

}