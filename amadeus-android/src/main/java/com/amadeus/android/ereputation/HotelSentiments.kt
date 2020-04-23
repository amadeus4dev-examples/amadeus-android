package com.amadeus.android.ereputation

import com.amadeus.android.BaseApi
import com.amadeus.android.domain.hotel.apis.HotelRatingsApi
import com.amadeus.android.tools.GeneratedCodeConverters
import kotlinx.coroutines.CoroutineDispatcher
import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.create

class HotelSentiments internal constructor(
    baseUrl: String,
    httpClient: OkHttpClient,
    dispatcher: CoroutineDispatcher
) : BaseApi(dispatcher) {

    override val basePath = "v2/"

    private val api: HotelRatingsApi = Retrofit.Builder()
        .baseUrl(baseUrl + basePath)
        .addConverterFactory(GeneratedCodeConverters.converterFactory())
        .client(httpClient)
        .build()
        .create()

    suspend fun get(
        ids: List<String>
    ) = safeApiCall {
        api.getSentimentsByHotelIds(ids)
    }

}