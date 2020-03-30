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

class FlightOffersSearch internal constructor(
    baseUrl: String,
    httpClient: OkHttpClient,
    dispatcher: CoroutineDispatcher
) : BaseApi(dispatcher) {

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

    suspend fun get(
        getFlightOffersBody: GetFlightOffersQuery
    ) = safeApiCall {
        api.searchFlightOffers(
            getFlightOffersBody
        )
    }
}