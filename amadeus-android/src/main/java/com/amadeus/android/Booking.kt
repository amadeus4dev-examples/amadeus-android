package com.amadeus.android

import com.amadeus.android.booking.FlightOrder
import com.amadeus.android.booking.FlightOrders
import com.amadeus.android.booking.HotelBooking
import com.squareup.moshi.Moshi
import kotlinx.coroutines.CoroutineDispatcher
import okhttp3.OkHttpClient

class Booking internal constructor(
    private val baseUrl: String,
    private val httpClient: OkHttpClient,
    private val moshi: Moshi,
    private val dispatcher: CoroutineDispatcher
) {

    /**
     * A namespaced client for the
     * `/v1/booking/flight-orders` endpoints.
     */
    val flightOrders = FlightOrders(baseUrl, httpClient, moshi, dispatcher)

    /**
     * A namespaced client for the
     * `/v1/booking/flight-orders/{id}` endpoints.
     */
    fun flightOrder(id: String) = FlightOrder(baseUrl, httpClient, dispatcher, id)

    /**
     * A namespaced client for the
     * `/v1/booking/hotel-booking` endpoints.
     */
    val hotelBooking = HotelBooking(baseUrl, httpClient, dispatcher)

}