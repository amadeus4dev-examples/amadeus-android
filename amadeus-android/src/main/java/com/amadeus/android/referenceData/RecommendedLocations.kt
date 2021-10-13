package com.amadeus.android.referenceData

import com.amadeus.android.BaseApi
import com.amadeus.android.domain.air.apis.CheckinLinksApi
import com.amadeus.android.domain.trip.apis.RecommendedLocationsApi
import com.amadeus.android.tools.GeneratedCodeConverters
import com.squareup.moshi.Moshi
import kotlinx.coroutines.CoroutineDispatcher
import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.create

class RecommendedLocations internal constructor(
    retrofit: Retrofit,
    dispatcher: CoroutineDispatcher
) : BaseApi(dispatcher) {

    private val api: RecommendedLocationsApi = retrofit.create()

    suspend fun get(
        cityCodes: String,
        travelerCountryCode: String? = null,
        destinationCountryCodes: String? = null
    ) = safeApiCall {
        api.getRecommendedLocations(cityCodes, travelerCountryCode, destinationCountryCodes)
    }
}