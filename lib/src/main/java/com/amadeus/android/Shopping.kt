package com.amadeus.android

import com.amadeus.android.shopping.*
import kotlinx.coroutines.CoroutineDispatcher
import okhttp3.OkHttpClient


class Shopping internal constructor(
    private val baseUrl: String,
    private val httpClient: OkHttpClient,
    private val dispatcher: CoroutineDispatcher
) {
    /**
     * A namespaced client for the
     * `/v1/shopping/flight-dates` endpoints.
     */
    val flightDates = FlightDates(baseUrl, httpClient, dispatcher)

    /**
     * A namespaced client for the
     * `/v1/shopping/flight-destinations` endpoints.
     */
    val flightDestinations = FlightDestinations(baseUrl, httpClient, dispatcher)

    /**
     * A namespaced client for the
     * `/v1/shopping/flight-offers` endpoints.
     */
    val flightOffers = FlightOffers(baseUrl, httpClient, dispatcher)

    /**
     * A namespaced client for the
     * `/v1/shopping/seatmaps` endpoints.
     */
    val seatMaps = SeatMaps(baseUrl, httpClient, dispatcher)

    /**
     * A namespaced client for the
     * `/v2/shopping/flight-offers` endpoints.
     */
    val flightOffersSearch = FlightOffersSearch(baseUrl, httpClient, dispatcher)

    /**
     * A namespaced client for the
     * `/v2/shopping/hotel-offers` endpoints.
     */
    val hotelOffers = HotelOffers(baseUrl, httpClient, dispatcher)

    /**
     * A namespaced client for the
     * `/v2/shopping/hotel-offers/by-hotel` endpoints.
     */
    val hotelOffersByHotel = HotelOffersByHotel(baseUrl, httpClient, dispatcher)

    /**
     * A namespaced client for the
     * `/v2/shopping/hotel-offers/:id` endpoints.
     */
    fun hotelOffer(offerId: String): HotelOffer {
        return HotelOffer(baseUrl, httpClient, dispatcher, offerId)
    }
}