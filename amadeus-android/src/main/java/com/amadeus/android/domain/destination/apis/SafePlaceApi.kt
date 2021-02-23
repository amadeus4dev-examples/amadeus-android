package com.amadeus.android.domain.destination.apis

import com.amadeus.android.ApiResponse
import com.amadeus.android.domain.resources.Activity
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

@JvmSuppressWildcards
interface SafePlaceApi {

    @GET("safety/safety-rated-locations")
    suspend fun getSafetyRatedLocations(
        @Query("latitude") latitude: Double,
        @Query("longitude") longitude: Double,
        @Query("radius") radius: Int?
    ): ApiResponse<List<Activity>>

    @GET("safety/safety-rated-locations/by-square")
    suspend fun getSafetyRatedLocationsBySquare(
        @Query("north") north: Double,
        @Query("west") west: Double,
        @Query("south") south: Double,
        @Query("east") east: Double
    ): ApiResponse<List<Activity>>

    @GET("safety/safety-rated-locations/{safePlaceId}")
    suspend fun getSafetyRatedLocation(
        @Path("safePlaceId") safePlaceId: String
    ): ApiResponse<Activity>
}