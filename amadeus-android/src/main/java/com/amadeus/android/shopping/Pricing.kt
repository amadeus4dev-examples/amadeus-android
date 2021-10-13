package com.amadeus.android.shopping

import com.amadeus.android.BaseApi
import com.amadeus.android.domain.air.apis.ShoppingApi
import com.amadeus.android.domain.resources.FlightOfferSearch
import com.amadeus.android.domain.resources.FlightPayment
import com.amadeus.android.domain.resources.Traveler
import com.amadeus.android.tools.GeneratedCodeConverters
import com.squareup.moshi.Moshi
import kotlinx.coroutines.CoroutineDispatcher
import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.create

@Suppress("BlockingMethodInNonBlockingContext")
class Pricing internal constructor(
    retrofit: Retrofit,
    dispatcher: CoroutineDispatcher
) : BaseApi(dispatcher) {

    private val api: ShoppingApi = retrofit.create()

    suspend fun post(
        body: String,
        include: List<String>? = null,
        forceClass: Boolean? = null
    ) = safeApiCall {
        api.quoteAirOffers(
            bodyAsMap(body),
            include,
            forceClass
        )
    }

    suspend fun post(
        body: Map<String, Any>,
        include: List<String>? = null,
        forceClass: Boolean? = null
    ) = safeApiCall {
        api.quoteAirOffers(
            body,
            include,
            forceClass
        )
    }

    suspend fun post(
        flightOffersSearch: FlightOfferSearch,
        payments: List<FlightPayment>? = null,
        travelers: List<Traveler>? = null,
        include: List<String>? = null,
        forceClass: Boolean? = null
    ) = post(
        listOf(flightOffersSearch),
        payments,
        travelers,
        include,
        forceClass
    )

    suspend fun post(
        flightOfferSearches: List<FlightOfferSearch>,
        payments: List<FlightPayment>? = null,
        travelers: List<Traveler>? = null,
        include: List<String>? = null,
        forceClass: Boolean? = null
    ) = safeApiCall {
        val bodyObject = mutableMapOf<String, Any>()
        bodyObject["type"] = "flight-offers-pricing"
        bodyObject["flightOffers"] = flightOfferSearches
        payments?.let { bodyObject["payments"] = it }
        travelers?.let { bodyObject["travelers"] = it }

        val body = mutableMapOf<String, Any>()
        body["data"] = bodyObject

        api.quoteAirOffers(body, include, forceClass)
    }
}
