package com.amadeus.android.unit.api.airport

import com.amadeus.android.Airport
import com.amadeus.android.domain.air.apis.AirportOntimePredictionApi
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
class AirportTests : BaseTest() {

    companion object {
        lateinit var airport: Airport

        @MockK
        lateinit var api: AirportOntimePredictionApi

        @BeforeClass
        @JvmStatic
        fun setup() {
            MockKAnnotations.init(this)

            // Setup mocks
            every { retrofit.create<AirportOntimePredictionApi>() } returns api

            // Setup tested implementation
            airport = Airport(retrofit, dispatcher)
        }
    }

    @Test
    fun `Predictions - getAirportOnTimePrediction`() = runBlockingTest {
        airport.predictions.onTime.get("", "")
        coVerify { api.getAirportOnTimePrediction(any(), any()) }
    }
}