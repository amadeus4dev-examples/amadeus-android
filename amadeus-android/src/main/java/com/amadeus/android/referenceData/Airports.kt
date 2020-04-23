package com.amadeus.android.referenceData

import com.amadeus.android.BaseApi
import com.amadeus.android.domain.air.apis.LocationApi
import com.amadeus.android.tools.GeneratedCodeConverters
import kotlinx.coroutines.CoroutineDispatcher
import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.create

class Airports internal constructor(
    baseUrl: String,
    httpClient: OkHttpClient,
    dispatcher: CoroutineDispatcher
) : BaseApi(dispatcher) {

    override val basePath = "v1/"

    private val api: LocationApi = Retrofit.Builder()
        .baseUrl(baseUrl + basePath)
        .addConverterFactory(GeneratedCodeConverters.converterFactory())
        .client(httpClient)
        .build()
        .create()

    suspend fun get(
        latitude: Double,
        longitude: Double,
        radius: Int? = null,
        pageLimit: Int? = null,
        pageOffset: Int? = null,
        sort: String? = null
    ) = safeApiCall {
        api.getNearestRelevantAirports(
            latitude,
            longitude,
            radius,
            pageLimit,
            pageOffset,
            sort
        )
    }
}