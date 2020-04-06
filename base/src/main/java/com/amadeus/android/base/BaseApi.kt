package com.amadeus.android.base

import com.amadeus.android.base.Result.Success
import com.squareup.moshi.Moshi
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.withContext
import retrofit2.Response

open class BaseApi(
    private val dispatcher: CoroutineDispatcher
) {

    open val basePath = "v1/"

    open val moshi = Moshi.Builder().build()

    /**
     * Wrap a suspending API [call] in try/catch. In case an exception is thrown, a [Result.Error] is
     * created based.
     */
    @Suppress("BlockingMethodInNonBlockingContext")
    suspend fun <T : Any> safeApiCall(call: suspend () -> ApiResponse<T>): Result<T>? {
        return withContext(dispatcher) {
            val response = call()
            if (response.isSuccessful && response.body() != null) {
                response.body()
            } else {
                moshi.adapter(Result.Error::class.java)
                    .fromJson(response.errorBody()?.string() ?: "")
            }
        }
    }
}

typealias ApiResponse<T> = Response<Success<T>>