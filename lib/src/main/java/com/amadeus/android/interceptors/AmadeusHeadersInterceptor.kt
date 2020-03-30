package com.amadeus.android.interceptors

import com.amadeus.android.BuildConfig
import okhttp3.Interceptor
import okhttp3.Response

internal class AmadeusHeadersInterceptor(
    private val customAppId: String?,
    private val customAppVersion: String?
) : Interceptor {

    override fun intercept(chain: Interceptor.Chain): Response {
        val authenticatedRequest = chain.request()
            .newBuilder()
            .addHeader(ACCEPT, "application/json, application/vnd.amadeus+json")
            .addHeader(CONTENT_TYPE, "application/vnd.amadeus+json")
            .addHeader(USER_AGENT, buildUserAgent())
            .build()
        return chain.proceed(authenticatedRequest)
    }

    private fun buildUserAgent(): String {
        val builder = StringBuilder("amadeus-android/${BuildConfig.VERSION_NAME}")
        if (customAppId != null && customAppVersion != null) {
            builder.append(" ")
            builder.append("$customAppId/$customAppVersion")
        }
        return builder.toString()
    }

    companion object {
        private const val USER_AGENT = "User-Agent"
        private const val ACCEPT = "Accept"
        private const val CONTENT_TYPE = "Content-Type"
    }
}