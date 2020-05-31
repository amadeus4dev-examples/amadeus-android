package com.amadeus.android.unit

import com.amadeus.android.token.AccessTokenInterceptor
import com.amadeus.android.token.AccessTokenProvider
import io.mockk.MockKAnnotations
import io.mockk.every
import io.mockk.impl.annotations.MockK
import io.mockk.verify
import okhttp3.Interceptor
import okhttp3.Request
import okhttp3.Response
import org.junit.Before
import org.junit.Test

class AccessTokenInterceptorTest {

    @MockK
    internal lateinit var tokenProvider: AccessTokenProvider

    @MockK
    lateinit var chain: Interceptor.Chain

    @MockK
    lateinit var response: Response

    @MockK
    lateinit var request: Request

    @MockK
    lateinit var builder: Request.Builder


    @Before
    fun setup() {
        MockKAnnotations.init(this)
        every { chain.request() } returns request
        every { request.newBuilder() } returns builder
        every { builder.removeHeader(any()) } returns builder
        every { builder.addHeader(any(), any()) } returns builder
        every { builder.build() } returns request
        every { chain.proceed(any()) } returns response
    }

    @Test
    fun `token not null, chain proceed with token`() {
        val interceptor = AccessTokenInterceptor(tokenProvider)
        every { tokenProvider.token() } returns "token"
        interceptor.intercept(chain)
        verify { tokenProvider.token() }
        verify { builder.addHeader(any(), any()) }
    }

    @Test
    fun `token null, chain proceed normally`() {
        val interceptor = AccessTokenInterceptor(tokenProvider)
        every { tokenProvider.token() } returns null
        interceptor.intercept(chain)
        verify { tokenProvider.token() }
        verify(exactly = 0) { builder.addHeader(any(), any()) }
    }

}