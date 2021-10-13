package com.amadeus.android.unit.api.analytics

import com.amadeus.android.Analytics
import com.amadeus.android.domain.air.apis.ItineraryPriceMetricsApi
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
class AnalyticsTests : BaseTest() {

    companion object {
        lateinit var analytics: Analytics

        @MockK
        lateinit var api: ItineraryPriceMetricsApi

        @BeforeClass
        @JvmStatic
        fun setup() {
            MockKAnnotations.init(this)

            // Setup mocks
            every { retrofit.create<ItineraryPriceMetricsApi>() } returns api

            // Setup tested implementation
            analytics = Analytics(retrofit, dispatcher)
        }
    }

    @Test
    fun `Analytics - getItineraryPriceMetrics`() = runBlockingTest {
        analytics.itineraryPriceMetrics.get("", "", "")
        coVerify {
            api.getItineraryPriceMetrics(
                any(),
                any(),
                any(),
                any(),
                any()
            )
        }
    }
}