package com.amadeus.android.shopping

import com.amadeus.android.BaseApi
import com.amadeus.android.domain.air.apis.DisplaySeatMapsApi
import com.amadeus.android.tools.GeneratedCodeConverters
import com.squareup.moshi.Moshi
import kotlinx.coroutines.CoroutineDispatcher
import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.create

@Suppress("BlockingMethodInNonBlockingContext")
class SeatMaps internal constructor(
    retrofit: Retrofit,
    dispatcher: CoroutineDispatcher
) : BaseApi(dispatcher) {

    private val api: DisplaySeatMapsApi = retrofit.create()

    @Throws(Exception::class)
    suspend fun post(body: String) = safeApiCall {
        api.getSeatmapFromFlightOffer(bodyAsMap(body))
    }

    @Throws(Exception::class)
    suspend fun post(body: Map<String, Any>) = safeApiCall {
        api.getSeatmapFromFlightOffer(body)
    }

    suspend fun get(flightOfferId: String) = safeApiCall {
        api.getSeatmapFromOrder(flightOfferId)
    }

}