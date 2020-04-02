package com.amadeus.android.shopping

import com.amadeus.android.base.BaseApi
import com.amadeus.android.domain.air.apis.ShoppingApi
import com.amadeus.android.domain.air.models.GetFlightOffersQuery
import com.amadeus.android.domain.air.tools.GeneratedCodeConverters
import kotlinx.coroutines.CoroutineDispatcher
import okhttp3.OkHttpClient
import org.threeten.bp.LocalDate
import retrofit2.Retrofit
import retrofit2.create
import java.io.IOException

class FlightOffersSearch internal constructor(
    baseUrl: String,
    httpClient: OkHttpClient,
    dispatcher: CoroutineDispatcher
) : BaseApi(dispatcher) {

    /**
     * A namespaced client for the
     * `/v2/shopping/flight-offers/pricing` endpoints.
     */
    val pricing = Pricing(baseUrl, httpClient, dispatcher)

    override val basePath = "v2/"

    private val api: ShoppingApi = Retrofit.Builder()
        .baseUrl(baseUrl + basePath)
        .addConverterFactory(GeneratedCodeConverters.converterFactory())
        .client(httpClient)
        .build()
        .create()

    suspend fun get(
        originLocationCode: String,
        destinationLocationCode: String,
        departureDate: LocalDate,
        adults: Int,
        returnDate: LocalDate? = null,
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

    suspend fun post(
        flightOffersBody: GetFlightOffersQuery
    ) = safeApiCall {
        api.searchFlightOffers(flightOffersBody)
    }

    @Throws(IOException::class)
    suspend fun post(
        flightOffersString: String
    ) = safeApiCall {
        val body = moshi.adapter(GetFlightOffersQuery::class.java).fromJson(flightOffersString)!!
        api.searchFlightOffers(body)
    }
}