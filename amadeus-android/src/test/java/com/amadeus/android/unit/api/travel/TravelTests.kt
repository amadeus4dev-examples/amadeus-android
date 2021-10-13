package com.amadeus.android.unit.api.travel

import com.amadeus.android.Travel
import com.amadeus.android.domain.air.apis.AirTrafficApi
import com.amadeus.android.domain.air.apis.FlightDelayPredictionApi
import com.amadeus.android.domain.trip.apis.TripParserJobsApi
import com.amadeus.android.domain.trip.apis.TripPurposePredictionApi
import com.amadeus.android.unit.api.BaseTest
import io.mockk.MockKAnnotations
import io.mockk.coVerify
import io.mockk.every
import io.mockk.impl.annotations.MockK
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.test.runBlockingTest
import org.junit.BeforeClass
import org.junit.Test
import retrofit2.create

@ExperimentalCoroutinesApi
class TravelTests : BaseTest() {

    companion object {
        lateinit var travel: Travel

        @MockK
        lateinit var tripPurposePredictionApi: TripPurposePredictionApi

        @MockK
        lateinit var flightDelayPredictionApi: FlightDelayPredictionApi

        @MockK
        lateinit var airPredictionApi: AirTrafficApi

        @MockK
        lateinit var tripParserJobApi: TripParserJobsApi

        @BeforeClass
        @JvmStatic
        fun setup() {
            MockKAnnotations.init(this)

            // Setup mocks
            every { retrofit.create<TripPurposePredictionApi>() } returns tripPurposePredictionApi
            every { retrofit.create<FlightDelayPredictionApi>() } returns flightDelayPredictionApi
            every { retrofit.create<AirTrafficApi>() } returns airPredictionApi
            every { retrofit.create<TripParserJobsApi>() } returns tripParserJobApi

            // Setup tested implementation
            travel = Travel(retrofit, dispatcher)
        }
    }

    @Test
    fun `AirTrafficApi - getAirTrafficBooked`() = runBlockingTest {
        travel.analytics.airTraffic.booked.get("", "")
        coVerify {
            airPredictionApi.getAirTrafficBooked(
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
    fun `AirTrafficApi - getAirTraffic`() = runBlockingTest {
        travel.analytics.airTraffic.busiestPeriod.get("", "")
        coVerify { airPredictionApi.getAirTraffic(any(), any(), any()) }
    }

    @Test
    fun `AirTrafficApi - getAirTrafficMostTraveled`() = runBlockingTest {
        travel.analytics.airTraffic.traveled.get("", "")
        coVerify {
            airPredictionApi.getAirTrafficMostTraveled(
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
    fun `FlightDelayPredictionApi - getFlightDelayPrediction`() = runBlockingTest {
        travel.predictions.flightDelay.get("", "", "", "", "", "", "", "", "", "")
        coVerify {
            flightDelayPredictionApi.getFlightDelayPrediction(
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
    fun `TripPurposePredictionApi - getTripPurposePrediction`() = runBlockingTest {
        travel.predictions.tripPurpose.get("", "", "", "")
        coVerify {
            tripPurposePredictionApi.getTripPurposePrediction(
                any(),
                any(),
                any(),
                any(),
                any()
            )
        }
    }
}