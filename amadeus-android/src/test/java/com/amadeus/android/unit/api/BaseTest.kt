package com.amadeus.android.unit.api

import io.mockk.MockKAnnotations
import io.mockk.every
import io.mockk.impl.annotations.MockK
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
        }
    }

}