package com.amadeus.android.unit.api.booking

import com.amadeus.android.Booking
import com.amadeus.android.domain.resources.FlightOfferSearch
import com.amadeus.android.domain.resources.FlightPrice
import com.amadeus.android.unit.api.BaseTest
import io.mockk.*
import io.mockk.impl.annotations.MockK
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.test.runBlockingTest
import org.junit.BeforeClass
import org.junit.Test
import retrofit2.create
import com.amadeus.android.domain.air.apis.BookingApi as AirBookingApi
import com.amadeus.android.domain.hotel.apis.BookingApi as HotelBookingApi

@ExperimentalCoroutinesApi
class BookingTests : BaseTest() {

    companion object {
        lateinit var booking: Booking

        @MockK
        lateinit var airBookingApi: AirBookingApi // import alias for air.apis.BookingApi

        @MockK
        lateinit var hotelBookingApi: HotelBookingApi // import alias for hotel.apis.BookingApi

        @BeforeClass
        @JvmStatic
        fun setup() {
            MockKAnnotations.init(this)

            // Setup mocks
            every { retrofit.create<AirBookingApi>() } returns airBookingApi
            every { retrofit.create<HotelBookingApi>() } returns hotelBookingApi

            // Setup tested implementation
            booking = Booking(retrofit, dispatcher)
        }
    }

    @Test
    fun `AirBooking - getFlightOrder`() = runBlockingTest {
        booking.flightOrder("").get()
        coVerify { airBookingApi.getFlightOrder(any()) }
    }

    @Test
    fun `AirBooking - cancelFlightOrder`() = runBlockingTest {
        booking.flightOrder("").delete()
        coVerify { airBookingApi.cancelFlightOrder(any()) }
    }

    @Test
    fun `AirBooking - createFlightOrders post string`() = runBlockingTest {
        booking.flightOrders.post("")
        verify { booking.flightOrders.bodyAsMap(any()) }
        coVerify { airBookingApi.createFlightOrders(any()) }
    }

    @Test
    fun `AirBooking - createFlightOrders post map`() = runBlockingTest {
        booking.flightOrders.post(mapOf())
        coVerify { airBookingApi.createFlightOrders(any()) }
    }

    @Test
    fun `AirBooking - createFlightOrders post FlightPrice`() = runBlockingTest {
        booking.flightOrders.post(mockk<FlightPrice>(relaxed = true))
        coVerify { airBookingApi.createFlightOrders(any()) }
    }

    @Test
    fun `AirBooking - createFlightOrders post FlightOfferSearch`() = runBlockingTest {
        booking.flightOrders.post(mockk<FlightOfferSearch>())
        coVerify { airBookingApi.createFlightOrders(any()) }
    }

    @Test
    fun `AirBooking - createFlightOrders post list FlightOfferSearch`() = runBlockingTest {
        booking.flightOrders.post(listOf())
        coVerify { airBookingApi.createFlightOrders(any()) }
    }

    @Test
    fun `HotelBooking - createBooking post string`() = runBlockingTest {
        booking.hotelBooking.post("")
        verify { booking.hotelBooking.bodyAsMap(any()) }
        coVerify { hotelBookingApi.createBooking(any()) }
    }

    @Test
    fun `HotelBooking - createBooking post map`() = runBlockingTest {
        booking.hotelBooking.post(mapOf())
        coVerify { hotelBookingApi.createBooking(any()) }
    }
}