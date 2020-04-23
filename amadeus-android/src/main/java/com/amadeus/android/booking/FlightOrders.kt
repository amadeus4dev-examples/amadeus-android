package com.amadeus.android.booking

import com.amadeus.android.BaseApi
import com.amadeus.android.domain.air.apis.BookingApi
import com.amadeus.android.tools.GeneratedCodeConverters
import com.squareup.moshi.Moshi
import kotlinx.coroutines.CoroutineDispatcher
import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.create

@Suppress("BlockingMethodInNonBlockingContext")
class FlightOrders internal constructor(
    baseUrl: String,
    httpClient: OkHttpClient,
    private val moshi: Moshi,
    dispatcher: CoroutineDispatcher
) : BaseApi(dispatcher) {

    override val basePath = "v1/"

    private val api: BookingApi = Retrofit.Builder()
        .baseUrl(baseUrl + basePath)
        .addConverterFactory(GeneratedCodeConverters.converterFactory(moshi))
        .client(httpClient)
        .build()
        .create()

    @Throws(Exception::class)
    suspend fun post(body: String) = safeApiCall {
        api.createFlightOrders(body)
    }
}