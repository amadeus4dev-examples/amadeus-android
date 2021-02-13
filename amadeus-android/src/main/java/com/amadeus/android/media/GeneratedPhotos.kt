package com.amadeus.android.media

import com.amadeus.android.BaseApi
import com.amadeus.android.domain.trip.apis.FilesApi
import com.amadeus.android.tools.GeneratedCodeConverters
import com.squareup.moshi.Moshi
import kotlinx.coroutines.CoroutineDispatcher
import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.create

class GeneratedPhotos internal constructor(
    retrofit: Retrofit,
    dispatcher: CoroutineDispatcher
) : BaseApi(dispatcher) {

    private val api: FilesApi = retrofit.create()

    suspend fun get(category: String) = safeApiCall { api.generateFile(category) }

}