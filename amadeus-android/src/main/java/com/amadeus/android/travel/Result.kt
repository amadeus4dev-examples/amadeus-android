package com.amadeus.android.travel

import com.amadeus.android.BaseApi
import com.amadeus.android.domain.trip.apis.TripParserJobsApi
import com.amadeus.android.tools.GeneratedCodeConverters
import com.squareup.moshi.Moshi
import kotlinx.coroutines.CoroutineDispatcher
import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.create

class Result internal constructor(
    baseUrl: String,
    httpClient: OkHttpClient,
    moshi: Moshi,
    dispatcher: CoroutineDispatcher,
    private val id: String = ""
) : BaseApi(moshi, dispatcher) {

    override val basePath = "v2/"

    private val api: TripParserJobsApi = Retrofit.Builder()
        .baseUrl(baseUrl + basePath)
        .addConverterFactory(GeneratedCodeConverters.converterFactory(moshi))
        .client(httpClient)
        .build()
        .create()

    //suspend fun get() = safeApiCall { api.getResult(id) }

}
