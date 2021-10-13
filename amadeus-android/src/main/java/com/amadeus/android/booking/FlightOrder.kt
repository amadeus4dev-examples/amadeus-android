package com.amadeus.android.booking

import com.amadeus.android.Amadeus
import com.amadeus.android.ApiResult
import com.amadeus.android.BaseApi
import com.amadeus.android.domain.air.apis.BookingApi
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.withContext
import retrofit2.Retrofit
import retrofit2.create
import java.io.IOException

class FlightOrder internal constructor(
    retrofit: Retrofit,
    private val dispatcher: CoroutineDispatcher,
    private val id: String
) : BaseApi(dispatcher) {

    private val api: BookingApi = retrofit.create()

    suspend fun get() = safeApiCall { api.getFlightOrder(id) }

    @Suppress("BlockingMethodInNonBlockingContext")
    suspend fun delete(): ApiResult<Any> {
        return withContext(dispatcher) {
            try {
                val response = api.cancelFlightOrder(id)
                if (response.isSuccessful) {
                    ApiResult.Success(Any())
                } else {
                    Amadeus.errorAdapter.fromJson(response.errorBody()?.string() ?: "")
                        ?: ApiResult.Error(exception = IOException("Impossible to parse error body."))
                }
            } catch (e: Exception) {
                ApiResult.Error(exception = e)
            }
        }
    }

}