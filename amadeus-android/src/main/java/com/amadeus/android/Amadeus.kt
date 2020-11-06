package com.amadeus.android

import android.content.Context
import androidx.annotation.VisibleForTesting
import com.amadeus.android.ApiResult.Success
import com.amadeus.android.interceptors.AmadeusHeadersInterceptor
import com.amadeus.android.model.AccessToken
import com.amadeus.android.service.AmadeusService
import com.amadeus.android.service.BaseService
import com.amadeus.android.token.AccessTokenAuthenticator
import com.amadeus.android.token.AccessTokenInterceptor
import com.amadeus.android.token.AccessTokenProvider
import com.amadeus.android.tools.NumbersAdapter
import com.amadeus.android.tools.TypesAdapterFactory
import com.amadeus.android.tools.XNullableAdapterFactory
import com.squareup.moshi.Moshi
import com.squareup.moshi.Types
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.moshi.MoshiConverterFactory
import retrofit2.create
import java.util.concurrent.TimeUnit

@Suppress("BlockingMethodInNonBlockingContext")
class Amadeus private constructor(
    private val baseUrl: String,
    private val clientId: String,
    private val clientSecret: String,
    private val logLevel: HttpLoggingInterceptor.Level,
    private val customAppId: String?,
    private val customAppVersion: String?,
    val dispatcher: CoroutineDispatcher
) : AccessTokenProvider {

    private val client = OkHttpClient.Builder()
        .connectTimeout(1, TimeUnit.MINUTES)
        .writeTimeout(1, TimeUnit.MINUTES)
        .readTimeout(1, TimeUnit.MINUTES)
        .addInterceptor(AmadeusHeadersInterceptor(customAppId, customAppVersion))
        .addInterceptor(HttpLoggingInterceptor().apply { level = logLevel })
        .addInterceptor(AccessTokenInterceptor(this))
        .authenticator(AccessTokenAuthenticator(this))
        .build()

    private val service: AmadeusService

    private val baseService: BaseService

    @VisibleForTesting
    val moshi = Moshi.Builder()
        .add(NumbersAdapter.FACTORY)
        .add(XNullableAdapterFactory())
        .add(TypesAdapterFactory())
        .build()

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
        baseService = Retrofit.Builder()
            .baseUrl(baseUrl)
            .client(client)
            .addConverterFactory(MoshiConverterFactory.create(moshi))
            .build()
            .create()

        referenceData = ReferenceData(baseUrl, client, moshi, dispatcher)
        shopping = Shopping(baseUrl, client, moshi, dispatcher)
        booking = Booking(baseUrl, client, moshi, dispatcher)
        airport = Airport(baseUrl, client, moshi, dispatcher)
        travel = Travel(baseUrl, client, moshi, dispatcher)
        ereputation = EReputation(baseUrl, client, moshi, dispatcher)
        media = Media(baseUrl, client, moshi, dispatcher)

        val okHttpClientBuilder = OkHttpClient.Builder()
            .addInterceptor(AmadeusHeadersInterceptor(customAppId, customAppVersion))
            .addInterceptor(HttpLoggingInterceptor().apply { level = logLevel })

        service = Retrofit.Builder()
            .baseUrl(baseUrl)
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

    @Throws(Exception::class)
    suspend fun get(url: String): String? {
        return withContext(dispatcher) {
            baseService.getByUrl(url).string()
        }
    }

    @Throws(Exception::class)
    suspend fun post(url: String, body: String?): String? {
        return withContext(dispatcher) {
            baseService.postByUrl(url, body).string()
        }
    }

    @Throws(Exception::class)
    suspend fun delete(url: String, body: String?): String? {
        return withContext(dispatcher) {
            baseService.deleteByUrl(url, body).string()
        }
    }

    @Throws(Exception::class)
    suspend inline fun <reified T> next(success: Success<List<T>>) = process(NEXT, success)

    @Throws(Exception::class)
    suspend inline fun <reified T> self(success: Success<List<T>>) = process(SELF, success)

    @Throws(Exception::class)
    suspend inline fun <reified T> previous(success: Success<List<T>>) = process(PREVIOUS, success)

    @Throws(Exception::class)
    suspend inline fun <reified T> first(success: Success<List<T>>) = process(FIRST, success)

    @Throws(Exception::class)
    suspend inline fun <reified T> last(success: Success<List<T>>) = process(LAST, success)

    @Throws(Exception::class)
    suspend inline fun <reified T> process(
        key: String,
        success: Success<List<T>>
    ): Success<List<T>>? {
        return withContext(dispatcher) {
            val result = success.meta?.links?.get(key)?.let {
                when (success.method) {
                    GET -> get(it)
                    POST -> post(it, null)
                    DELETE -> delete(it, null)
                    else -> null
                }
            }
            result?.let {
                val type = Types.newParameterizedType(
                    List::class.java,
                    T::class.java
                )
                val resultType = Types.newParameterizedTypeWithOwner(
                    ApiResult::class.java,
                    Success::class.java,
                    type
                )
                val moshi = Moshi.Builder()
                    .add(XNullableAdapterFactory())
                    .add(TypesAdapterFactory())
                    .build()
                val adapter = moshi.adapter<Success<List<T>>>(resultType)

                val mapType = Types.newParameterizedType(
                    Map::class.java,
                    String::class.java,
                    Any::class.java
                )
                val resultAsMap = moshi.adapter<MutableMap<String, Any>>(mapType).fromJson(it) ?: mutableMapOf()
                resultAsMap["method"] = success.method ?: ""

                try {
                    return@withContext adapter.fromJsonValue(resultAsMap)
                } catch (e: Exception) {
                    return@withContext null
                }
            }
            null
        }
    }

    /**
     * Amadeus Builder for client configuration setup.
     * @param context: Context of your app used to configure date parser.
     */
    class Builder internal constructor() {
        private var hostName: String = Hosts.TEST.value
        private lateinit var clientId: String
        private lateinit var clientSecret: String
        private var logLevel: HttpLoggingInterceptor.Level = HttpLoggingInterceptor.Level.NONE
        private var customAppId: String? = null
        private var customAppVersion: String? = null
        private var dispatcher: CoroutineDispatcher = Dispatchers.IO

        constructor(context: Context) : this() {
            context.getString(R.string.amadeus_client_id)
                .takeIf { it.isNotBlank() }
                ?.let {
                    clientId = it
                }
            context.getString(R.string.amadeus_client_secret)
                .takeIf { it.isNotBlank() }
                ?.let {
                    clientSecret = it
                }
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

    companion object {

        // HTTP verbs
        const val GET = "GET"
        const val POST = "POST"
        const val DELETE = "DELETE"

        // Pagination
        const val FIRST = "first"
        const val LAST = "last"
        const val NEXT = "next"
        const val SELF = "self"
        const val PREVIOUS = "previous"
    }
}