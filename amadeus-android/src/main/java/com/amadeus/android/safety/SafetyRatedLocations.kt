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
    baseUrl: String,
    httpClient: OkHttpClient,
    moshi: Moshi,
    dispatcher: CoroutineDispatcher
) : BaseApi(moshi, dispatcher) {

    val bySquare = BySquare(baseUrl, httpClient, moshi, dispatcher)

    override val basePath = "v1/"

    private val api: SafePlaceApi = Retrofit.Builder()
        .baseUrl(baseUrl + basePath)
        .addConverterFactory(GeneratedCodeConverters.converterFactory(moshi))
        .client(httpClient)
        .build()
        .create()

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