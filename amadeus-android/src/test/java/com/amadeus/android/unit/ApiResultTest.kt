package com.amadeus.android.unit

import com.amadeus.android.ApiResult
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


}