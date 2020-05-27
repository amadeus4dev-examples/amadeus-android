package com.amadeus.android.booking

import com.amadeus.android.ApiResult
import com.amadeus.android.BaseApi
import com.amadeus.android.domain.air.apis.BookingApi
import com.amadeus.android.tools.GeneratedCodeConverters
import com.squareup.moshi.Moshi
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.withContext
import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.create

class FlightOrder internal constructor(
    baseUrl: String,
    httpClient: OkHttpClient,
    private val moshi: Moshi,
    private val dispatcher: CoroutineDispatcher,
    private val id: String
) : BaseApi(moshi, dispatcher) {

    override val basePath = "v1/"

    private val api: BookingApi = Retrofit.Builder()
        .baseUrl(baseUrl + basePath)
        .addConverterFactory(GeneratedCodeConverters.converterFactory(moshi))
        .client(httpClient)
        .build()
        .create()

    suspend fun get() = safeApiCall { api.getFlightOrder(id) }

    @Suppress("BlockingMethodInNonBlockingContext")
    suspend fun delete(): ApiResult<Any> {
        return withContext(dispatcher) {
            try {
                val response = api.cancelFlightOrder(id)
                if (response.isSuccessful) {
                    ApiResult.Success(Any())
                } else {
                    moshi.adapter(ApiResult.Error::class.java)
                        .fromJson(response.errorBody()?.string() ?: "") ?: ApiResult.Error()
                }
            } catch (e: Exception) {
                ApiResult.Error()
            }
        }
    }

}