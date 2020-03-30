package com.amadeus.android.token

import okhttp3.Interceptor
import okhttp3.Response

internal class AccessTokenInterceptor(
    private val tokenProvider: AccessTokenProvider
) : Interceptor {

    override fun intercept(chain: Interceptor.Chain): Response {
        val token = tokenProvider.token()

        return if (token == null) {
            chain.proceed(chain.request())
        } else {
            val authenticatedRequest = chain.request()
                .newBuilder()
                .addHeader(AUTHORIZATION, "$token")
                .build()
            chain.proceed(authenticatedRequest)
        }
    }

    companion object {
        private const val AUTHORIZATION = "Authorization"
    }
}