package com.amadeus.android.travel

import com.amadeus.android.BaseApi
import com.amadeus.android.domain.air.apis.AirTrafficApi
import com.amadeus.android.tools.GeneratedCodeConverters
import com.squareup.moshi.Moshi
import kotlinx.coroutines.CoroutineDispatcher
import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.create
import java.math.BigDecimal

class Traveled internal constructor(
    retrofit: Retrofit,
    dispatcher: CoroutineDispatcher
) : BaseApi(dispatcher) {

    private val api: AirTrafficApi = retrofit.create()

    suspend fun get(
        originCityCode: String,
        period: String,
        max: BigDecimal? = null,
        fields: String? = null,
        pageLimit: Int? = null,
        pageOffset: Int? = null,
        sort: String? = null
    ) = safeApiCall {
        api.getAirTrafficMostTraveled(originCityCode, period, max, fields, pageLimit, pageOffset, sort)
    }
}