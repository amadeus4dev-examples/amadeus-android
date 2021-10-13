package com.amadeus.android.referenceData

import com.amadeus.android.BaseApi
import com.amadeus.android.domain.air.apis.AirlinesApi
import com.amadeus.android.tools.GeneratedCodeConverters
import com.squareup.moshi.Moshi
import kotlinx.coroutines.CoroutineDispatcher
import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.create

class Airlines internal constructor(
    retrofit: Retrofit,
    dispatcher: CoroutineDispatcher
) : BaseApi(dispatcher) {

    private val api: AirlinesApi = retrofit.create()

    suspend fun get(airlineCodes: String? = null) = safeApiCall {
        api.getairlines(airlineCodes)
    }

}