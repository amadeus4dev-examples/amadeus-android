package com.amadeus.android.unit.api.shopping

import com.amadeus.android.Amadeus
import com.amadeus.android.Shopping
import com.amadeus.android.domain.air.apis.DisplaySeatMapsApi
import com.amadeus.android.domain.air.apis.FlightDatesApi
import com.amadeus.android.domain.air.apis.FlightDestinationsApi
import com.amadeus.android.domain.destination.apis.ActivitiesApi
import com.amadeus.android.unit.api.BaseTest
import io.mockk.*
import io.mockk.impl.annotations.MockK
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.test.runBlockingTest
import org.junit.BeforeClass
import org.junit.Test
import retrofit2.create
import com.amadeus.android.domain.air.apis.ShoppingApi as AirShoppingApi
import com.amadeus.android.domain.hotel.apis.ShoppingApi as HotelShoppingApi

@ExperimentalCoroutinesApi
class ShoppingTests : BaseTest() {

    companion object {
        lateinit var shopping: Shopping

        @MockK
        lateinit var flightDatesApi: FlightDatesApi

        @MockK
        lateinit var flightDestinationsApi: FlightDestinationsApi

        @MockK
        lateinit var airShoppingApi: AirShoppingApi // Alias import of air.apis.Shopping

        @MockK
        lateinit var displaySeatMapsApi: DisplaySeatMapsApi

        @MockK
        lateinit var hotelShoppingApi: HotelShoppingApi // Alias import of hotel.apis.Shopping

        @MockK
        lateinit var activitiesApi: ActivitiesApi

        @BeforeClass
        @JvmStatic
        fun setup() {
            MockKAnnotations.init(this)

            // Setup mocks
            every { retrofit.create<FlightDatesApi>() } returns flightDatesApi
            every { retrofit.create<FlightDestinationsApi>() } returns flightDestinationsApi
            every { retrofit.create<AirShoppingApi>() } returns airShoppingApi
            every { retrofit.create<DisplaySeatMapsApi>() } returns displaySeatMapsApi
            every { retrofit.create<HotelShoppingApi>() } returns hotelShoppingApi
            every { retrofit.create<ActivitiesApi>() } returns activitiesApi

            // Setup tested implementation
            shopping = Shopping(retrofit, dispatcher)
        }
    }

    @Test
    fun `ActivitiesApi - getActivitiesBySquare`() = runBlockingTest {
        shopping.activities.bySquare.get(0.0, 0.0, 0.0, 0.0)
        coVerify { activitiesApi.getActivitiesBySquare(any(), any(), any(), any()) }
    }

    @Test
    fun `ActivitiesApi - getActivities`() = runBlockingTest {
        shopping.activities.get(0.0, 0.0)
        coVerify { activitiesApi.getActivities(any(), any(), any()) }
    }

    @Test
    fun `ActivitiesApi - getActivity`() = runBlockingTest {
        shopping.activity("").get()
        coVerify { activitiesApi.getActivity("") }
    }

    @Test
    fun `FlightDatesApi - getFlightDates`() = runBlockingTest {
        shopping.flightDates.get("", "")
        coVerify {
            flightDatesApi.getFlightDates(
                any(),
                any(),
                any(),
                any(),
                any(),
                any(),
                any(),
                any()
            )
        }
    }

    @Test
    fun `FlightDestinationsApi - getFlightDestinations`() = runBlockingTest {
        shopping.flightDestinations.get("")
        coVerify {
            flightDestinationsApi.getFlightDestinations(
                any(),
                any(),
                any(),
                any(),
                any(),
                any(),
                any()
            )
        }
    }

    @Test
    fun `AirShoppingApi - getFlightOffers`() = runBlockingTest {
        shopping.flightOffers.get("", "", "", 0)
        coVerify {
            airShoppingApi.getFlightOffers(
                any(),
                any(),
                any(),
                any(),
                any(),
                any(),
                any(),
                any(),
                any(),
                any(),
                any(),
                any(),
                any(),
                any()
            )
        }
    }

    @Test
    fun `AirShoppingApi - post string`() = runBlockingTest {
        Amadeus.mapAdapter = mockk(relaxed = true)
        every { Amadeus.mapAdapter.fromJson("") } returns mapOf()
        
        shopping.flightOffers.post("")
        verify { shopping.flightOffers.bodyAsMap("") }
        coVerify { airShoppingApi.searchFlightOffers(any()) }
    }

    @Test
    fun `AirShoppingApi - post map`() = runBlockingTest {
        shopping.flightOffers.post(mapOf())
        coVerify { airShoppingApi.searchFlightOffers(any()) }
    }
}