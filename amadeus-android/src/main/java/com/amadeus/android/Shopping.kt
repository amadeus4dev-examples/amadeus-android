package com.amadeus.android

import com.amadeus.android.shopping.*
import kotlinx.coroutines.CoroutineDispatcher
import retrofit2.Retrofit


class Shopping internal constructor(
    private val retrofit: Retrofit,
    private val dispatcher: CoroutineDispatcher
) {
    /**
     * A namespaced client for the
     * `/v1/shopping/flight-dates` endpoints.
     */
    val flightDates = FlightDates(retrofit, dispatcher)

    /**
     * A namespaced client for the
     * `/v1/shopping/flight-destinations` endpoints.
     */
    val flightDestinations = FlightDestinations(retrofit, dispatcher)

    /**
     * A namespaced client for the
     * `/v1/shopping/flight-offers` endpoints.
     */
    val flightOffers = FlightOffers(retrofit, dispatcher)

    /**
     * A namespaced client for the
     * `/v1/shopping/seatmaps` endpoints.
     */
    val seatMaps = SeatMaps(retrofit, dispatcher)

    /**
     * A namespaced client for the
     * `/v2/shopping/flight-offers` endpoints.
     */
    val flightOffersSearch = FlightOffersSearch(retrofit, dispatcher)

    /**
     * A namespaced client for the
     * `/v2/shopping/hotel-offers` endpoints.
     */
    val hotelOffers = HotelOffers(retrofit, dispatcher)

    /**
     * A namespaced client for the
     * `/v2/shopping/hotel-offers/by-hotel` endpoints.
     */
    val hotelOffersByHotel = HotelOffersByHotel(retrofit, dispatcher)

    /**
     * A namespaced client for the
     * `/v1/shopping/activities` endpoints.
     */
    val activities = Activities(retrofit, dispatcher)

    /**
     * A namespaced client for the
     * `/v1/shopping/activities/:id` endpoints.
     */
    fun activity(activityId: String) = Activity(retrofit, dispatcher, activityId)

    /**
     * A namespaced client for the
     * `/v2/shopping/hotel-offers/:id` endpoints.
     */
    fun hotelOffer(offerId: String) = HotelOffer(retrofit, dispatcher, offerId)
}