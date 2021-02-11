package com.amadeus.android.domain.destination.apis

import com.amadeus.android.ApiResponse
import com.amadeus.android.domain.resources.Activity
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

@JvmSuppressWildcards
interface ActivitiesApi {

    @GET("shopping/activities")
    suspend fun getActivities(
        @Query("latitude") latitude: Double,
        @Query("longitude") longitude: Double,
        @Query("radius") radius: Int?
    ): ApiResponse<List<Activity>>

    @GET("shopping/activities/by-square")
    suspend fun getActivitiesBySquare(
        @Query("north") north: Double,
        @Query("west") west: Double,
        @Query("south") south: Double,
        @Query("east") east: Double
    ): ApiResponse<List<Activity>>

    @GET("shopping/activities/{activityId}")
    suspend fun getActivity(
        @Path("activityId") activityId: String
    ): ApiResponse<Activity>
}