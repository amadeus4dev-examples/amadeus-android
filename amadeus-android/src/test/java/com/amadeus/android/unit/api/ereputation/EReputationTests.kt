package com.amadeus.android.unit.api.ereputation

import com.amadeus.android.EReputation
import com.amadeus.android.domain.hotel.apis.HotelRatingsApi
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
class EReputationTests : BaseTest() {

    companion object {
        lateinit var ereputation: EReputation

        @MockK
        lateinit var api: HotelRatingsApi

        @BeforeClass
        @JvmStatic
        fun setup() {
            MockKAnnotations.init(this)

            // Setup mocks
            every { retrofit.create<HotelRatingsApi>() } returns api

            // Setup tested implementation
            ereputation = EReputation(retrofit, dispatcher)
        }
    }

    @Test
    fun `EReputation - getSentimentsByHotelIds`() = runBlockingTest {
        ereputation.hotelSentiments.get(listOf())
        coVerify { api.getSentimentsByHotelIds(any()) }
    }
}