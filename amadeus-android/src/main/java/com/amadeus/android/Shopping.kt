package com.amadeus.android

import com.amadeus.android.shopping.*
import com.amadeus.android.shopping.Activities
import com.squareup.moshi.Moshi
import kotlinx.coroutines.CoroutineDispatcher
import okhttp3.OkHttpClient


class Shopping internal constructor(
    private val baseUrl: String,
    private val httpClient: OkHttpClient,
    private val moshi: Moshi,
    private val dispatcher: CoroutineDispatcher
) {
    /**
     * A namespaced client for the
     * `/v1/shopping/flight-dates` endpoints.
     */
    val flightDates = FlightDates(baseUrl, httpClient, moshi, dispatcher)

    /**
     * A namespaced client for the
     * `/v1/shopping/flight-destinations` endpoints.
     */
    val flightDestinations = FlightDestinations(baseUrl, httpClient, moshi, dispatcher)

    /**
     * A namespaced client for the
     * `/v1/shopping/flight-offers` endpoints.
     */
    val flightOffers = FlightOffers(baseUrl, httpClient, moshi, dispatcher)

    /**
     * A namespaced client for the
     * `/v1/shopping/seatmaps` endpoints.
     */
    val seatMaps = SeatMaps(baseUrl, httpClient, moshi, dispatcher)

    /**
     * A namespaced client for the
     * `/v2/shopping/flight-offers` endpoints.
     */
    val flightOffersSearch = FlightOffersSearch(baseUrl, httpClient, moshi, dispatcher)

    /**
     * A namespaced client for the
     * `/v2/shopping/hotel-offers` endpoints.
     */
    val hotelOffers = HotelOffers(baseUrl, httpClient, moshi, dispatcher)

    /**
     * A namespaced client for the
     * `/v2/shopping/hotel-offers/by-hotel` endpoints.
     */
    val hotelOffersByHotel = HotelOffersByHotel(baseUrl, httpClient, moshi, dispatcher)

    /**
     * A namespaced client for the
     * `/v1/shopping/activities` endpoints.
     */
    val activities = Activities(baseUrl, httpClient, moshi, dispatcher)

    /**
     * A namespaced client for the
     * `/v1/shopping/activities/:id` endpoints.
     */
    fun activity(activityId: String) = Activity(baseUrl, httpClient, moshi, dispatcher, activityId)

    /**
     * A namespaced client for the
     * `/v2/shopping/hotel-offers/:id` endpoints.
     */
    fun hotelOffer(offerId: String) = HotelOffer(baseUrl, httpClient, moshi, dispatcher, offerId)
}