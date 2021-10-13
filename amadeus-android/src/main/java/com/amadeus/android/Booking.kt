package com.amadeus.android

import com.amadeus.android.booking.FlightOrder
import com.amadeus.android.booking.FlightOrders
import com.amadeus.android.booking.HotelBooking
import kotlinx.coroutines.CoroutineDispatcher
import retrofit2.Retrofit

class Booking internal constructor(
    private val retrofit: Retrofit,
    private val dispatcher: CoroutineDispatcher
) {

    /**
     * A namespaced client for the
     * `/v1/booking/flight-orders` endpoints.
     */
    val flightOrders = FlightOrders(retrofit, dispatcher)

    /**
     * A namespaced client for the
     * `/v1/booking/flight-orders/{id}` endpoints.
     */
    fun flightOrder(id: String) = FlightOrder(retrofit, dispatcher, id)

    /**
     * A namespaced client for the
     * `/v1/booking/hotel-booking` endpoints.
     */
    val hotelBooking = HotelBooking(retrofit, dispatcher)

}