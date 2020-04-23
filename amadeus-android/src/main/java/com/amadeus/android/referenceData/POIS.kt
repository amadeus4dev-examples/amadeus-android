package com.amadeus.android.referenceData

import com.amadeus.android.BaseApi
import com.amadeus.android.domain.trip.apis.SearchApi
import com.amadeus.android.tools.CSV
import com.amadeus.android.tools.GeneratedCodeConverters
import kotlinx.coroutines.CoroutineDispatcher
import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.create

class POIS internal constructor(
    baseUrl: String,
    httpClient: OkHttpClient,
    dispatcher: CoroutineDispatcher
) : BaseApi(dispatcher) {

    /**
     * A namespaced client for the
     * `/v1/reference-data/locations/pois/by-square` endpoints.
     */
    val bySquare = BySquare(baseUrl, httpClient, dispatcher)

    override val basePath = "v1/"

    private val api: SearchApi = Retrofit.Builder()
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
        @CSV categories: List<String>? = null
    ) = safeApiCall {
        api.getPointsOfInterest(latitude, longitude, radius, pageLimit, pageOffset, categories)
    }

}