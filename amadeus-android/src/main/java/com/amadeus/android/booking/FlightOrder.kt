package com.amadeus.android.booking

import com.amadeus.android.BaseApi
import com.amadeus.android.domain.air.apis.BookingApi
import com.amadeus.android.tools.GeneratedCodeConverters
import kotlinx.coroutines.CoroutineDispatcher
import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.create

class FlightOrder internal constructor(
    baseUrl: String,
    httpClient: OkHttpClient,
    dispatcher: CoroutineDispatcher,
    private val id: String
) : BaseApi(dispatcher) {

    override val basePath = "v1/"

    private val api: BookingApi = Retrofit.Builder()
        .baseUrl(baseUrl + basePath)
        .addConverterFactory(GeneratedCodeConverters.converterFactory())
        .client(httpClient)
        .build()
        .create()

    suspend fun get() = safeApiCall { api.getFlightOrder(id) }

    suspend fun delete() = safeApiCall { api.cancelFlightOrder(id) }

}