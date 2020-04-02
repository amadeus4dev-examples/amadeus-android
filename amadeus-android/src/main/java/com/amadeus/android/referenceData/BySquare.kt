package com.amadeus.android.referenceData

import com.amadeus.android.base.BaseApi
import com.amadeus.android.domain.trip.apis.SearchApi
import com.amadeus.android.domain.trip.tools.CSV
import com.amadeus.android.domain.trip.tools.GeneratedCodeConverters
import kotlinx.coroutines.CoroutineDispatcher
import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.create

class BySquare internal constructor(
    baseUrl: String,
    httpClient: OkHttpClient,
    dispatcher: CoroutineDispatcher
) : BaseApi(dispatcher) {

    override val basePath = "v1/"

    private val api: SearchApi = Retrofit.Builder()
        .baseUrl(baseUrl + basePath)
        .addConverterFactory(GeneratedCodeConverters.converterFactory())
        .client(httpClient)
        .build()
        .create()

    suspend fun get(
        north: Double,
        west: Double,
        south: Double,
        east: Double,
        pageLimit: Int? = null,
        pageOffset: Int? = null,
        @CSV categories: List<String>? = null
    ) = safeApiCall {
        api.getPointsOfInterestBySquare(north, west, south, east, pageLimit, pageOffset, categories)
    }

}