package com.amadeus.android.domain.trip.apis

import com.amadeus.android.ApiResponse
import com.amadeus.android.domain.resources.Location
import retrofit2.http.GET

interface RecommendedLocationsApi {

    @GET("reference-data/recommended-locations")
    suspend fun getRecommendedLocations(
        @retrofit2.http.Query("cityCodes") cityCodes: String,
        @retrofit2.http.Query("travelerCountryCode") travelerCountryCode: String?,
        @retrofit2.http.Query("destinationCountryCodes") destinationCountryCodes: String?
    ): ApiResponse<List<Location>>
}