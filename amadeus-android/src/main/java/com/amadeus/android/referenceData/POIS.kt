package com.amadeus.android.referenceData

import com.amadeus.android.BaseApi
import com.amadeus.android.domain.destination.apis.POIsApi
import com.amadeus.android.referenceData.POIs.BySquare
import com.amadeus.android.tools.CSV
import com.amadeus.android.tools.GeneratedCodeConverters
import com.squareup.moshi.Moshi
import kotlinx.coroutines.CoroutineDispatcher
import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.create

class POIS internal constructor(
    retrofit: Retrofit,
    dispatcher: CoroutineDispatcher
) : BaseApi(dispatcher) {

    /**
     * A namespaced client for the
     * `/v1/reference-data/locations/pois/by-square` endpoints.
     */
    val bySquare = BySquare(retrofit, dispatcher)

    private val api: POIsApi = retrofit.create()

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