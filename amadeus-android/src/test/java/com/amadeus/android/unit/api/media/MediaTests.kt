package com.amadeus.android.unit.api.media

import com.amadeus.android.Media
import com.amadeus.android.domain.trip.apis.FilesApi
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
class MediaTests : BaseTest() {

    companion object {
        lateinit var media: Media

        @MockK
        lateinit var api: FilesApi

        @BeforeClass
        @JvmStatic
        fun setup() {
            MockKAnnotations.init(this)

            // Setup mocks
            every { retrofit.create<FilesApi>() } returns api

            // Setup tested implementation
            media = Media(retrofit, dispatcher)
        }
    }

    @Test
    fun `FilesApi - generateFile`() = runBlockingTest {
        media.files.generatedPhotos.get("")
        coVerify { api.generateFile(any()) }
    }
}