package com.amadeus.android.unit

import com.amadeus.android.ApiResult
import com.amadeus.android.ApiResult.Success.Meta
import com.amadeus.android.succeeded
import org.junit.Test

class ApiResultTest {

    @Test
    fun `Success with object - succeeded`() {
        assert(ApiResult.Success(Any()).succeeded)
    }

    @Test
    fun `Success with object - not succeeded`() {
        assert(!ApiResult.Success<Any?>(null).succeeded)
    }

    @Test
    fun `Error - not succeeded`() {
        assert(!ApiResult.Error(listOf()).succeeded)
    }

    @Test
    fun `Has first - true`() {
        val result = ApiResult.Success<Any?>(
            null,
            Meta(0, mapOf(Pair("first", "url")))
        )
        assert(result.hasFirst())
    }

    @Test
    fun `Has first - false`() {
        val result = ApiResult.Success<Any?>(
            null,
            Meta(0, mapOf(Pair("no_first", "url")))
        )
        assert(!result.hasFirst())
    }

    @Test
    fun `Has last - true`() {
        val result = ApiResult.Success<Any?>(
            null,
            Meta(0, mapOf(Pair("last", "url")))
        )
        assert(result.hasLast())
    }

    @Test
    fun `Has last - false`() {
        val result = ApiResult.Success<Any?>(
            null,
            Meta(0, mapOf(Pair("no_last", "url")))
        )
        assert(!result.hasLast())
    }

    @Test
    fun `Has previous - true`() {
        val result = ApiResult.Success<Any?>(
            null,
            Meta(0, mapOf(Pair("previous", "url")))
        )
        assert(result.hasPrevious())
    }

    @Test
    fun `Has previous - false`() {
        val result = ApiResult.Success<Any?>(
            null,
            Meta(0, mapOf(Pair("no_previous", "url")))
        )
        assert(!result.hasPrevious())
    }

    @Test
    fun `Has next - true`() {
        val result = ApiResult.Success<Any?>(
            null,
            Meta(0, mapOf(Pair("next", "url")))
        )
        assert(result.hasNext())
    }

    @Test
    fun `Has next - false`() {
        val result = ApiResult.Success<Any?>(
            null,
            Meta(0, mapOf(Pair("no_next", "url")))
        )
        assert(!result.hasNext())
    }

    @Test
    fun `Has self - true`() {
        val result = ApiResult.Success<Any?>(
            null,
            Meta(0, mapOf(Pair("self", "url")))
        )
        assert(result.hasSelf())
    }

    @Test
    fun `Has self - false`() {
        val result = ApiResult.Success<Any?>(
            null,
            Meta(0, mapOf(Pair("no_self", "url")))
        )
        assert(!result.hasSelf())
    }

}