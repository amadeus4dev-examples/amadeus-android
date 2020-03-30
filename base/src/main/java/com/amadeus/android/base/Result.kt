package com.amadeus.android.base

import com.amadeus.android.base.Result.Success
import com.squareup.moshi.JsonClass

/**
 * A generic class that holds a value with its loading status.
 * @param <T>
 */
sealed class Result<out T> {

    @JsonClass(generateAdapter = true)
    data class Success<out T> internal constructor(
        internal val meta: Meta?,
        val data: T,
        val dictionaries: Map<String, Any>?
    ) : Result<T>() {

        @JsonClass(generateAdapter = true)
        data class Meta(val count: Int?, val links: Map<String, String>?)
    }

    @JsonClass(generateAdapter = true)
    data class Error internal constructor(
        val errors: List<Issue> = ArrayList()
    ) : Result<Nothing>() {

        @JsonClass(generateAdapter = true)
        data class Issue internal constructor(
            val status: Int? = null,
            val code: Int? = null,
            val title: String? = null,
            val detail: String? = null,
            val source: Source? = null
        )

        @JsonClass(generateAdapter = true)
        data class Source internal constructor(
            val pointer: String? = null,
            val parameter: String? = null,
            val example: String? = null
        )
    }
}

/**
 * `true` if [Result] is of type [Success] & holds non-null [Success.data].
 */
val Result<*>.succeeded
    get() = this is Success && data != null