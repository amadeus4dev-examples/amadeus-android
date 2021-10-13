package com.amadeus.android.unit.api.safety

import com.amadeus.android.Safety
import com.amadeus.android.domain.destination.apis.SafePlaceApi
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
class SafetyTests : BaseTest() {

    companion object {
        lateinit var safety: Safety

        @MockK
        lateinit var api: SafePlaceApi

        @BeforeClass
        @JvmStatic
        fun setup() {
            MockKAnnotations.init(this)

            // Setup mocks
            every { retrofit.create<SafePlaceApi>() } returns api

            // Setup tested implementation
            safety = Safety(retrofit, dispatcher)
        }
    }

    @Test
    fun `Safety - getSafetyRatedLocation`() = runBlockingTest {
        safety.safetyRatedLocation("").get()
        coVerify { api.getSafetyRatedLocation(any()) }
    }

    @Test
    fun `Safety - getSafetyRatedLocations`() = runBlockingTest {
        safety.safetyRatedLocations.get(0.0, 0.0)
        coVerify { api.getSafetyRatedLocations(any(), any(), any()) }
    }

    @Test
    fun `Safety - getSafetyRatedLocationsBySquare`() = runBlockingTest {
        safety.safetyRatedLocations.bySquare.get(0.0, 0.0, 0.0, 0.0)
        coVerify { api.getSafetyRatedLocationsBySquare(any(), any(), any(), any()) }
    }
}