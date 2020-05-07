package com.amadeus.android

import com.amadeus.android.ApiResult.Success
import com.squareup.moshi.Moshi
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.withContext
import retrofit2.Response

open class BaseApi(
    private val dispatcher: CoroutineDispatcher
) {

    open val basePath = "v1/"

    private val moshi = Moshi.Builder().build()

    /**
     * Wrap a suspending API [call] in try/catch. In case an exception is thrown, a [ApiResult.Error] is
     * created based.
     */
    @Suppress("BlockingMethodInNonBlockingContext")
    suspend fun <T : Any> safeApiCall(call: suspend () -> ApiResponse<T>): ApiResult<T>? {
        return withContext(dispatcher) {
            val response = call()
            if (response.isSuccessful && response.body() != null) {
                response.body()?.apply { method = response.raw().request.method }
            } else {
                moshi.adapter(ApiResult.Error::class.java)
                    .fromJson(response.errorBody()?.string() ?: "")
            }
        }
    }
}

typealias ApiResponse<T> = Response<Success<T>>