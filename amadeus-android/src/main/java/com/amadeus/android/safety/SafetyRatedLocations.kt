package com.amadeus.android.safety

import com.amadeus.android.BaseApi
import com.amadeus.android.domain.destination.apis.SafePlaceApi
import com.amadeus.android.safety.safetyRatedLocations.BySquare
import com.amadeus.android.tools.GeneratedCodeConverters
import com.squareup.moshi.Moshi
import kotlinx.coroutines.CoroutineDispatcher
import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.create

class SafetyRatedLocations internal constructor(
    retrofit: Retrofit,
    dispatcher: CoroutineDispatcher
) : BaseApi(dispatcher) {

    val bySquare = BySquare(retrofit, dispatcher)

    private val api: SafePlaceApi = retrofit.create()

    suspend fun get(
        latitude: Double,
        longitude: Double,
        radius: Int? = null
    ) = safeApiCall {
        api.getSafetyRatedLocations(
            latitude,
            longitude,
            radius
        )
    }

}