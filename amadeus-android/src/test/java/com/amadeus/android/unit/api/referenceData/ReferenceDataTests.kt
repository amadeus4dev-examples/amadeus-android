package com.amadeus.android.unit.api.referenceData

import com.amadeus.android.ReferenceData
import com.amadeus.android.domain.air.apis.AirlinesApi
import com.amadeus.android.domain.air.apis.CheckinLinksApi
import com.amadeus.android.domain.air.apis.LocationApi
import com.amadeus.android.domain.destination.apis.POIsApi
import com.amadeus.android.domain.trip.apis.RecommendedLocationsApi
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
class ReferenceDataTests : BaseTest() {

    companion object {
        lateinit var referenceData: ReferenceData

        @MockK
        lateinit var checkinLinksApi: CheckinLinksApi

        @MockK
        lateinit var locationApi: LocationApi

        @MockK
        lateinit var poisApi: POIsApi

        @MockK
        lateinit var airlinesApi: AirlinesApi

        @MockK
        lateinit var recommendedLocationsApi: RecommendedLocationsApi

        @BeforeClass
        @JvmStatic
        fun setup() {
            MockKAnnotations.init(this)

            // Setup mocks
            every { retrofit.create<CheckinLinksApi>() } returns checkinLinksApi
            every { retrofit.create<LocationApi>() } returns locationApi
            every { retrofit.create<POIsApi>() } returns poisApi
            every { retrofit.create<AirlinesApi>() } returns airlinesApi
            every { retrofit.create<RecommendedLocationsApi>() } returns recommendedLocationsApi

            // Setup tested implementation
            referenceData = ReferenceData(retrofit, dispatcher)
        }
    }

    @Test
    fun `CheckinLinksApi - getCheckinURLs`() = runBlockingTest {
        referenceData.urls.checkinLinks.get("")
        coVerify { checkinLinksApi.getCheckinURLs(any(), any()) }
    }

    @Test
    fun `LocationApi - getAirportCity`() = runBlockingTest {
        referenceData.location("").get()
        coVerify { locationApi.getAirportCity(any()) }
    }

    @Test
    fun `LocationApi - getAirportCitySearch`() = runBlockingTest {
        referenceData.locations.get(emptyList(), "")
        coVerify {
            locationApi.getAirportCitySearch(
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
    fun `LocationApi - getNearestRelevantAirports`() = runBlockingTest {
        referenceData.locations.airports.get(0.0, 0.0)
        coVerify {
            locationApi.getNearestRelevantAirports(
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
    fun `POIsApi - getPointOfInterest`() = runBlockingTest {
        referenceData.locations.pointsOfInterest("").get()
        coVerify { poisApi.getPointOfInterest(any()) }
    }

    @Test
    fun `POIsApi - getPointsOfInterest`() = runBlockingTest {
        referenceData.locations.pointsOfInterest.get(0.0, 0.0)
        coVerify { poisApi.getPointsOfInterest(any(), any(), any(), any(), any(), any()) }
    }

    @Test
    fun `POIsApi - getPointsOfInterestBySquare`() = runBlockingTest {
        referenceData.locations.pointsOfInterest.bySquare.get(0.0, 0.0, 0.0, 0.0)
        coVerify {
            poisApi.getPointsOfInterestBySquare(
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
    fun `AirlinesApi - getairlines`() = runBlockingTest {
        referenceData.airlines.get("")
        coVerify { airlinesApi.getairlines(any()) }
    }

    @Test
    fun `RecommendedLocationsApi - getRecommendedLocations`() = runBlockingTest {
        referenceData.recommendedLocations.get("")
        coVerify { recommendedLocationsApi.getRecommendedLocations(any(), any(), any()) }
    }

}