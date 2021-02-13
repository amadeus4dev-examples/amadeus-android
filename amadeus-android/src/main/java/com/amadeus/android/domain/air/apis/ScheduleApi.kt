package com.amadeus.android.domain.air.apis

import com.amadeus.android.ApiResponse
import com.amadeus.android.domain.resources.Airline
import com.amadeus.android.domain.resources.DatedFlight
import retrofit2.http.GET

@JvmSuppressWildcards
interface ScheduleApi {
    @GET("v2/schedule/flights")
    suspend fun getFlightStatus(
        @retrofit2.http.Query("carrierCode") carrierCode: String,
        @retrofit2.http.Query("flightNumber") flightNumber: String,
        @retrofit2.http.Query("scheduledDepartureDate") scheduledDepartureDate: String,
        @retrofit2.http.Query("operationalSuffix") operationalSuffix: String?
    ): ApiResponse<List<DatedFlight>>
}
