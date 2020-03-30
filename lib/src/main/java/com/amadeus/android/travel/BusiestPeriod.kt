package com.amadeus.android.travel

import com.amadeus.android.base.BaseApi
import com.amadeus.android.domain.air.apis.AirTrafficApi
import com.amadeus.android.domain.air.tools.GeneratedCodeConverters
import kotlinx.coroutines.CoroutineDispatcher
import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.create

class BusiestPeriod internal constructor(
    baseUrl: String,
    httpClient: OkHttpClient,
    dispatcher: CoroutineDispatcher
) : BaseApi(dispatcher) {

    override val basePath = "v1/"

    private val api: AirTrafficApi = Retrofit.Builder()
        .baseUrl(baseUrl + basePath)
        .addConverterFactory(GeneratedCodeConverters.converterFactory())
        .client(httpClient)
        .build()
        .create()

    suspend fun get(
        cityCode: String,
        period: String,
        direction: String? = null
    ) = safeApiCall {
        api.getAirTraffic(cityCode, period, direction)
    }

}