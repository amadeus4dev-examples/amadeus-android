package com.amadeus.android.unit

import com.amadeus.android.token.AccessTokenAuthenticator
import com.amadeus.android.token.AccessTokenProvider
import io.mockk.MockKAnnotations
import io.mockk.every
import io.mockk.impl.annotations.MockK
import io.mockk.mockk
import io.mockk.verify
import okhttp3.Request
import okhttp3.Response
import org.junit.Before
import org.junit.Test

class AccessTokenAuthenticatorTest {

    @MockK
    internal lateinit var tokenProvider: AccessTokenProvider

    @Before
    fun setup() = MockKAnnotations.init(this)

    @Test
    fun `token is not expired, refresh token not called`() {
        val authenticator = AccessTokenAuthenticator(tokenProvider)
        every { tokenProvider.isTokenNullOrExpired() } returns false
        authenticator.authenticate(mockk(), mockk())
        verify { tokenProvider.isTokenNullOrExpired() }
        verify(exactly = 0) { tokenProvider.refreshToken() }
    }

    @Test
    fun `token is not expired, refresh token called`() {
        val response = mockk<Response>()
        val request = mockk<Request>()
        val builder = mockk<Request.Builder>()
        every { response.request } returns request
        every { request.newBuilder() } returns builder
        every { builder.removeHeader(any()) } returns builder
        every { builder.addHeader(any(), any()) } returns builder
        every { builder.build() } returns request

        val authenticator = AccessTokenAuthenticator(tokenProvider)
        every { tokenProvider.isTokenNullOrExpired() } returns true
        every { tokenProvider.refreshToken() } returns "token"
        authenticator.authenticate(mockk(), response)
        verify { tokenProvider.isTokenNullOrExpired() }
        verify { tokenProvider.refreshToken() }
    }

}