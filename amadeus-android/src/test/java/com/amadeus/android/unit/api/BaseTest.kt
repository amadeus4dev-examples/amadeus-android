package com.amadeus.android.unit.api

import com.amadeus.android.Amadeus
import io.mockk.MockKAnnotations
import io.mockk.every
import io.mockk.impl.annotations.MockK
import io.mockk.mockk
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.test.TestCoroutineDispatcher
import kotlinx.coroutines.test.setMain
import org.junit.BeforeClass
import retrofit2.Retrofit

@ExperimentalCoroutinesApi
abstract class BaseTest {

    companion object {

        @MockK
        lateinit var retrofit: Retrofit

        val dispatcher = TestCoroutineDispatcher()

        @BeforeClass
        @JvmStatic
        fun setupApi() {
            MockKAnnotations.init(this)
            Dispatchers.setMain(dispatcher)

            Amadeus.mapAdapter = mockk(relaxed = true)
            every { Amadeus.mapAdapter.fromJson("") } returns mapOf()

            Amadeus.errorAdapter = mockk(relaxed = true)
            every { Amadeus.errorAdapter.fromJson("") } returns mockk()
        }
    }

}