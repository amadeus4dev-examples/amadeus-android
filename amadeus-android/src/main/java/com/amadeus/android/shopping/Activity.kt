package com.amadeus.android.shopping

import com.amadeus.android.BaseApi
import com.amadeus.android.domain.destination.apis.ActivitiesApi
import com.amadeus.android.tools.GeneratedCodeConverters
import com.squareup.moshi.Moshi
import kotlinx.coroutines.CoroutineDispatcher
import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.create

class Activity internal constructor(
    baseUrl: String,
    httpClient: OkHttpClient,
    moshi: Moshi,
    dispatcher: CoroutineDispatcher,
    private val id: String
) : BaseApi(moshi, dispatcher) {

    override val basePath = "v1/"

    private val api: ActivitiesApi = Retrofit.Builder()
        .baseUrl(baseUrl + basePath)
        .addConverterFactory(GeneratedCodeConverters.converterFactory(moshi))
        .client(httpClient)
        .build()
        .create()

    suspend fun get() = safeApiCall {
        api.getActivity(id)
    }

}