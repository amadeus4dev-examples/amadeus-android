package com.amadeus.android

import com.amadeus.android.ApiResult.Success
import com.squareup.moshi.Moshi
import com.squareup.moshi.Types
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.withContext
import retrofit2.Response
import java.io.IOException

open class BaseApi(
    private val moshi: Moshi,
    private val dispatcher: CoroutineDispatcher
) {

    open val basePath = "v1/"

    /**
     * Wrap a suspending API [call] in try/catch. In case an exception is thrown, a [ApiResult.Error] is
     * created based.
     */
    @Suppress("BlockingMethodInNonBlockingContext")
    suspend fun <T : Any> safeApiCall(call: suspend () -> ApiResponse<T>): ApiResult<T> {
        return withContext(dispatcher) {
            try {
                val response = call()
                val body = response.body()
                if (response.isSuccessful && body != null) {
                    body.apply {
                        method = response.raw().request.method
                        code = response.code()
                    }
                } else {
                    moshi.adapter(ApiResult.Error::class.java)
                        .fromJson(response.errorBody()?.string() ?: "")
                        ?: ApiResult.Error(exception = IOException("Impossible to parse error body."))
                }
            } catch (e: Exception) {
                ApiResult.Error(exception = e)
            }
        }
    }

    fun bodyAsMap(body: String): Map<String, Any> {
        val type = Types.newParameterizedType(
            Map::class.java,
            String::class.java,
            Any::class.java
        )
        return moshi.adapter<Map<String, Any>>(type).fromJson(body) ?: emptyMap()
    }
}

typealias ApiResponse<T> = Response<Success<T>>