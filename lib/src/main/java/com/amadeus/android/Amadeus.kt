package com.amadeus.android

import android.content.Context
import com.amadeus.android.interceptors.AmadeusHeadersInterceptor
import com.amadeus.android.model.AccessToken
import com.amadeus.android.service.AmadeusService
import com.amadeus.android.token.AccessTokenAuthenticator
import com.amadeus.android.token.AccessTokenInterceptor
import com.amadeus.android.token.AccessTokenProvider
import com.jakewharton.threetenabp.AndroidThreeTen
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.Dispatchers
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.moshi.MoshiConverterFactory
import retrofit2.create
import java.util.concurrent.TimeUnit

class Amadeus private constructor(
    baseUrl: String,
    private val clientId: String,
    private val clientSecret: String,
    logLevel: HttpLoggingInterceptor.Level,
    customAppId: String?,
    customAppVersion: String?,
    dispatcher: CoroutineDispatcher
) : AccessTokenProvider {

    private var service: AmadeusService
    private var token: AccessToken? = null
    private var tokenValidUntil = 0L

    /**
     * A namespaced client for the `/v2/reference-data` endpoints.
     */
    val referenceData: ReferenceData

    /**
     * A namespaced client for the `/v1/travel` endpoints.
     * A namespaced client for the `/v2/travel` endpoints.
     */
    val travel: Travel

    /**
     * A namespaced client for the `/v1/shopping` endpoints.
     */
    val shopping: Shopping

    /**
     * A namespaced client for the `/v1/booking` endpoints.
     */
    val booking: Booking

    /**
     * A namespaced client for the `/v2/e-reputation` endpoints.
     */
    val ereputation: EReputation

    /**
     * A namespace client for the `/v1/airport` endpoints.
     */
    val airport: Airport

    /**
     * A namespace client for the `/v2/media` endpoints.
     */
    val media: Media

    init {
        val client = OkHttpClient.Builder()
            .connectTimeout(1, TimeUnit.MINUTES)
            .writeTimeout(1, TimeUnit.MINUTES)
            .readTimeout(1, TimeUnit.MINUTES)
            .addInterceptor(AmadeusHeadersInterceptor(customAppId, customAppVersion))
            .addInterceptor(HttpLoggingInterceptor().apply { level = logLevel })
            .addInterceptor(AccessTokenInterceptor(this))
            .authenticator(AccessTokenAuthenticator(this))
            .build()

        referenceData = ReferenceData(baseUrl, client, dispatcher)
        shopping = Shopping(baseUrl, client, dispatcher)
        booking = Booking(baseUrl, client, dispatcher)
        airport = Airport(baseUrl, client, dispatcher)
        travel = Travel(baseUrl, client, dispatcher)
        ereputation = EReputation(baseUrl, client, dispatcher)
        media = Media(baseUrl, client, dispatcher)

        val okHttpClientBuilder = OkHttpClient.Builder()
            .addInterceptor(AmadeusHeadersInterceptor(customAppId, customAppVersion))
            .addInterceptor(HttpLoggingInterceptor().apply { level = logLevel })

        service = Retrofit.Builder()
            .baseUrl("https://test.api.amadeus.com/")
            .client(okHttpClientBuilder.build())
            .addConverterFactory(MoshiConverterFactory.create())
            .build()
            .create()
    }

    override fun refreshToken(): String? {
        if (token == null || System.currentTimeMillis() >= tokenValidUntil) {
            service.getAccessToken(clientId, clientSecret)
                .execute()
                .takeIf { it.isSuccessful && it.body() != null }?.let { response ->
                response.body()?.let {
                    tokenValidUntil = System.currentTimeMillis() + (it.expiresIn * 1000)
                    token = it
                }
            }
        }
        return token()
    }

    override fun token() = token?.authorization

    override fun isTokenNullOrExpired() =
        token == null || System.currentTimeMillis() >= tokenValidUntil


    /**
     * Amadeus Builder for client configuration setup.
     * @param context: Context of your app used to configure date parser.
     */
    class Builder(
        context: Context?
    ) {
        private var hostName: String = Hosts.TEST.value
        private lateinit var clientId: String
        private lateinit var clientSecret: String
        private var logLevel: HttpLoggingInterceptor.Level = HttpLoggingInterceptor.Level.NONE
        private var customAppId: String? = null
        private var customAppVersion: String? = null
        private var dispatcher: CoroutineDispatcher = Dispatchers.IO

        init {
            context?.let { AndroidThreeTen.init(it) }
        }

        enum class Hosts(val value: String) {
            TEST("https://test.api.amadeus.com/"),
            PRODUCTION("https://api.amadeus.com/")
        }

        enum class LogLevel(val value: HttpLoggingInterceptor.Level) {
            /** No logs. */
            NONE(HttpLoggingInterceptor.Level.NONE),

            /**
             * Logs request and response lines.
             *
             * Example:
             * ```
             * --> POST /greeting http/1.1 (3-byte body)
             *
             * <-- 200 OK (22ms, 6-byte body)
             * ```
             */
            BASIC(HttpLoggingInterceptor.Level.BASIC),

            /**
             * Logs request and response lines and their respective headers.
             *
             * Example:
             * ```
             * --> POST /greeting http/1.1
             * Host: example.com
             * Content-Type: plain/text
             * Content-Length: 3
             * --> END POST
             *
             * <-- 200 OK (22ms)
             * Content-Type: plain/text
             * Content-Length: 6
             * <-- END HTTP
             * ```
             */
            HEADERS(HttpLoggingInterceptor.Level.HEADERS),

            /**
             * Logs request and response lines and their respective headers and bodies (if present).
             *
             * Example:
             * ```
             * --> POST /greeting http/1.1
             * Host: example.com
             * Content-Type: plain/text
             * Content-Length: 3
             *
             * Hi?
             * --> END POST
             *
             * <-- 200 OK (22ms)
             * Content-Type: plain/text
             * Content-Length: 6
             *
             * Hello!
             * <-- END HTTP
             * ```
             */
            BODY(HttpLoggingInterceptor.Level.BODY)
        }

        /**
         * Set client default host name.
         * Default Hosts.TEST.
         */
        fun setHostName(hostName: Hosts) = apply { this.hostName = hostName.value }

        /**
         * Required: set client id.
         */
        fun setClientId(clientId: String) = apply { this.clientId = clientId }

        /**
         * Required: set client secret.
         */
        fun setClientSecret(clientSecret: String) = apply { this.clientSecret = clientSecret }

        /**
         * Set client log level.
         * Default LogLevel.NONE.
         */
        fun setLogLevel(logLevel: LogLevel) = apply { this.logLevel = logLevel.value }

        fun setCustomAppIdAndVersion(appId: String, appVersion: String) = apply {
            this.customAppId = appId
            this.customAppVersion = appVersion
        }

        /**
         * Set client coroutine dispatcher, this should be use for tests purposes only.
         * Default Dispatchers.IO.
         */
        fun setDispatcher(dispatcher: CoroutineDispatcher) = apply { this.dispatcher = dispatcher }

        fun build() = Amadeus(
            hostName,
            clientId,
            clientSecret,
            logLevel,
            customAppId,
            customAppVersion,
            dispatcher
        )
    }
}
