package com.amadeus.android

import com.amadeus.android.media.Files
import kotlinx.coroutines.CoroutineDispatcher
import retrofit2.Retrofit

class Media internal constructor(
    retrofit: Retrofit,
    dispatcher: CoroutineDispatcher
) {

    /**
     * A namespaced client for the
     * `/v2/media/files` endpoints.
     */
    val files = Files(retrofit, dispatcher)

}