package com.amadeus.android.shopping.activities

import com.amadeus.android.BaseApi
import com.amadeus.android.domain.destination.apis.ActivitiesApi
import com.amadeus.android.tools.GeneratedCodeConverters
import com.squareup.moshi.Moshi
import kotlinx.coroutines.CoroutineDispatcher
import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.create

class BySquare internal constructor(
    retrofit: Retrofit,
    dispatcher: CoroutineDispatcher
) : BaseApi(dispatcher) {

    private val api: ActivitiesApi = retrofit.create()

    suspend fun get(
        north: Double,
        west: Double,
        south: Double,
        east: Double
    ) = safeApiCall {
        api.getActivitiesBySquare(
            north,
            west,
            south,
            east
        )
    }

}