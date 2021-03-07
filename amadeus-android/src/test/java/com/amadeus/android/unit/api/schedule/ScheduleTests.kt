package com.amadeus.android.unit.api.schedule

import com.amadeus.android.Schedule
import com.amadeus.android.domain.air.apis.ScheduleApi
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
class ScheduleTests : BaseTest() {

    companion object {
        lateinit var schedule: Schedule

        @MockK
        lateinit var api: ScheduleApi

        @BeforeClass
        @JvmStatic
        fun setup() {
            MockKAnnotations.init(this)

            // Setup mocks
            every { retrofit.create<ScheduleApi>() } returns api

            // Setup tested implementation
            schedule = Schedule(retrofit, dispatcher)
        }
    }

    @Test
    fun `Schedule - getFlightStatus`() = runBlockingTest {
        schedule.flights.get("", "", "")
        coVerify { api.getFlightStatus(any(), any(), any(), any()) }
    }
}