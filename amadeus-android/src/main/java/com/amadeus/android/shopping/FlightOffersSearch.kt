package com.amadeus.android.shopping

import com.amadeus.android.BaseApi
import com.amadeus.android.domain.air.apis.ShoppingApi
import com.amadeus.android.tools.GeneratedCodeConverters
import com.squareup.moshi.Moshi
import kotlinx.coroutines.CoroutineDispatcher
import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.create

@Suppress("BlockingMethodInNonBlockingContext")
class FlightOffersSearch internal constructor(
    baseUrl: String,
    httpClient: OkHttpClient,
    moshi: Moshi,
    dispatcher: CoroutineDispatcher
) : BaseApi(moshi, dispatcher) {

    /**
     * A namespaced client for the
     * `/v2/shopping/flight-offers/pricing` endpoints.
     */
    val pricing = Pricing(baseUrl, httpClient, moshi, dispatcher)

    override val basePath = "v2/"

    private val api: ShoppingApi = Retrofit.Builder()
        .baseUrl(baseUrl + basePath)
        .addConverterFactory(GeneratedCodeConverters.converterFactory(moshi))
        .client(httpClient)
        .build()
        .create()

    suspend fun get(
        originLocationCode: String,
        destinationLocationCode: String,
        departureDate: String,
        adults: Int,
        returnDate: String? = null,
        children: Int? = null,
        infants: Int? = null,
        travelClass: String? = null,
        includedAirlineCodes: String? = null,
        excludedAirlineCodes: String? = null,
        nonStop: Boolean? = null,
        currencyCode: String? = null,
        maxPrice: Int? = null,
        max: Int? = null
    ) = safeApiCall {
        api.getFlightOffers(
            originLocationCode,
            destinationLocationCode,
            departureDate,
            adults,
            returnDate,
            children,
            infants,
            travelClass,
            includedAirlineCodes,
            excludedAirlineCodes,
            nonStop,
            currencyCode,
            maxPrice,
            max
        )
    }

    @Throws(Exception::class)
    suspend fun post(body: String) = safeApiCall {
        api.searchFlightOffers(bodyAsMap(body))
    }

    @Throws(Exception::class)
    suspend fun post(body: Map<String, Any>) = safeApiCall {
        api.searchFlightOffers(body)
    }
}