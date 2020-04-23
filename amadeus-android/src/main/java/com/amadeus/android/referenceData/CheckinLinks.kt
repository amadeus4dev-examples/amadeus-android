package com.amadeus.android.referenceData

import com.amadeus.android.BaseApi
import com.amadeus.android.domain.air.apis.CheckinLinksApi
import com.amadeus.android.tools.GeneratedCodeConverters
import kotlinx.coroutines.CoroutineDispatcher
import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.create

class CheckinLinks internal constructor(
    baseUrl: String,
    httpClient: OkHttpClient,
    dispatcher: CoroutineDispatcher
) : BaseApi(dispatcher) {

    override val basePath = "v2/"

    private val api: CheckinLinksApi = Retrofit.Builder()
        .baseUrl(baseUrl + basePath)
        .addConverterFactory(GeneratedCodeConverters.converterFactory())
        .client(httpClient)
        .build()
        .create()

    suspend fun get(airlineCode: String, language: String? = null) = safeApiCall {
        api.getCheckinURLs(airlineCode, language)
    }
}