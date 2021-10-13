package com.amadeus.android.shopping

import com.amadeus.android.BaseApi
import com.amadeus.android.domain.destination.apis.ActivitiesApi
import com.amadeus.android.shopping.activities.BySquare
import com.amadeus.android.tools.GeneratedCodeConverters
import com.squareup.moshi.Moshi
import kotlinx.coroutines.CoroutineDispatcher
import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.create

class Activities internal constructor(
    retrofit: Retrofit,
    dispatcher: CoroutineDispatcher
) : BaseApi(dispatcher) {

    val bySquare = BySquare(retrofit, dispatcher)

    private val api: ActivitiesApi = retrofit.create()

    suspend fun get(
        latitude: Double,
        longitude: Double,
        radius: Int? = null
    ) = safeApiCall {
        api.getActivities(
            latitude,
            longitude,
            radius
        )
    }

}