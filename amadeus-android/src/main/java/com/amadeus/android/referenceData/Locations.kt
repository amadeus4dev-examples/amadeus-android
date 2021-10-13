package com.amadeus.android.referenceData

import com.amadeus.android.BaseApi
import com.amadeus.android.domain.air.apis.LocationApi
import com.amadeus.android.tools.GeneratedCodeConverters
import com.squareup.moshi.Moshi
import kotlinx.coroutines.CoroutineDispatcher
import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.create

class Locations internal constructor(
    private val retrofit: Retrofit,
    private val dispatcher: CoroutineDispatcher
) : BaseApi(dispatcher) {

    /**
     * A namespaced client for the
     * `/v2/reference-data/locations/airports` endpoints.
     */
    val airports = Airports(retrofit, dispatcher)

    /**
     * A namespaced client for the
     * `/v1/reference-data/locations/pointsOfInterest` endpoints.
     */
    val pointsOfInterest = POIS(retrofit, dispatcher)

    private val api: LocationApi = retrofit.create()

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

    fun pointsOfInterest(id: String) = POI(retrofit, dispatcher, id)

}