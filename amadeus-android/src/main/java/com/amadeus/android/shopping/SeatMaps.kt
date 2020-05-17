package com.amadeus.android.shopping

import com.amadeus.android.BaseApi
import com.amadeus.android.domain.air.apis.DisplaySeatMapsApi
import com.amadeus.android.tools.GeneratedCodeConverters
import com.squareup.moshi.Moshi
import com.squareup.moshi.Types
import kotlinx.coroutines.CoroutineDispatcher
import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.create

@Suppress("BlockingMethodInNonBlockingContext")
class SeatMaps internal constructor(
    baseUrl: String,
    httpClient: OkHttpClient,
    private val moshi: Moshi,
    dispatcher: CoroutineDispatcher
) : BaseApi(moshi, dispatcher) {

    override val basePath = "v1/"

    private val api: DisplaySeatMapsApi = Retrofit.Builder()
        .baseUrl(baseUrl + basePath)
        .addConverterFactory(GeneratedCodeConverters.converterFactory(moshi))
        .client(httpClient)
        .build()
        .create()

    @Throws(Exception::class)
    suspend fun post(body: String) = safeApiCall {
        val type = Types.newParameterizedType(
            Map::class.java,
            String::class.java,
            Any::class.java
        )
        val bodyMap = moshi.adapter<Map<String, Any>>(type).fromJson(body) ?: emptyMap()
        api.getSeatmapFromFlightOffer(bodyMap)
    }

    @Throws(Exception::class)
    suspend fun post(body: Map<String, Any>) = safeApiCall {
        api.getSeatmapFromFlightOffer(body)
    }

    suspend fun get(flightOfferId: String) = safeApiCall {
        api.getSeatmapFromOrder(flightOfferId)
    }

}