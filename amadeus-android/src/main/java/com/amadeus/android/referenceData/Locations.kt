package com.amadeus.android.referenceData

import com.amadeus.android.base.BaseApi
import com.amadeus.android.domain.air.apis.LocationApi
import com.amadeus.android.domain.air.tools.GeneratedCodeConverters
import kotlinx.coroutines.CoroutineDispatcher
import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.create

class Locations internal constructor(
    private val baseUrl: String,
    private val httpClient: OkHttpClient,
    private val dispatcher: CoroutineDispatcher
) : BaseApi(dispatcher) {

    /**
     * A namespaced client for the
     * `/v2/reference-data/locations/airports` endpoints.
     */
    val airports = Airports(baseUrl, httpClient, dispatcher)

    /**
     * A namespaced client for the
     * `/v1/reference-data/locations/pointsOfInterest` endpoints.
     */
    val pointsOfInterest = POIS(baseUrl, httpClient, dispatcher)

    override val basePath = "v1/"

    private val api: LocationApi = Retrofit.Builder()
        .baseUrl(baseUrl + basePath)
        .addConverterFactory(GeneratedCodeConverters.converterFactory())
        .client(httpClient)
        .build()
        .create()

    suspend fun get(
        subType: List<String>,
        keyword: String,
        countryCode: String? = null,
        pageLimit: Int? = null,
        pageOffset: Int? = null,
        sort: String? = null,
        view: String? = null
    ) = safeApiCall {
        api.getAirportCitySearch(subType, keyword, countryCode, pageLimit, pageOffset, sort, view)
    }

    fun pointsOfInterest(id: String) = POI(baseUrl, httpClient, dispatcher, id)

}