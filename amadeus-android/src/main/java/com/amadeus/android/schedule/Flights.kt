package com.amadeus.android.schedule

import com.amadeus.android.BaseApi
import com.amadeus.android.domain.air.apis.ScheduleApi
import com.amadeus.android.tools.GeneratedCodeConverters
import com.squareup.moshi.Moshi
import kotlinx.coroutines.CoroutineDispatcher
import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.create

class Flights internal constructor(
    retrofit: Retrofit,
    dispatcher: CoroutineDispatcher
) : BaseApi(dispatcher) {

    private val api: ScheduleApi = retrofit.create()

    suspend fun get(
        carrierCode: String,
        flightNumber: String,
        scheduleDepartureDate: String,
        operationalSuffix: String? = null
    ) = safeApiCall {
        api.getFlightStatus(
            carrierCode,
            flightNumber,
            scheduleDepartureDate,
            operationalSuffix
        )
    }

}