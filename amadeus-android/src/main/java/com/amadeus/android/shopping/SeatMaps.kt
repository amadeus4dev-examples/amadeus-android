package com.amadeus.android.shopping

import com.amadeus.android.base.BaseApi
import com.amadeus.android.domain.air.apis.DisplaySeatMapsApi
import com.amadeus.android.domain.air.models.FlightOffers
import com.amadeus.android.domain.air.tools.GeneratedCodeConverters
import com.squareup.moshi.Moshi
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
) : BaseApi(dispatcher) {

    override val basePath = "v1/"

    private val api: DisplaySeatMapsApi = Retrofit.Builder()
        .baseUrl(baseUrl + basePath)
        .addConverterFactory(GeneratedCodeConverters.converterFactory(moshi))
        .client(httpClient)
        .build()
        .create()

    @Throws(Exception::class)
    suspend fun post(flightOffersString: String) = safeApiCall {
        val body = moshi.adapter(FlightOffers::class.java).fromJson(flightOffersString)!!
        api.getSeatmapFromFlightOffer(body)
    }

    suspend fun post(flightOffers: FlightOffers) = safeApiCall {
        api.getSeatmapFromFlightOffer(flightOffers)
    }

    suspend fun get(flightOfferId: String) = safeApiCall {
        api.getSeatmapFromOrder(flightOfferId)
    }

}