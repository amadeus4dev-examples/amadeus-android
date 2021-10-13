package com.amadeus.android.booking

import com.amadeus.android.BaseApi
import com.amadeus.android.domain.hotel.apis.BookingApi
import com.amadeus.android.tools.GeneratedCodeConverters
import com.squareup.moshi.Moshi
import kotlinx.coroutines.CoroutineDispatcher
import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.create

class HotelBooking internal constructor(
    retrofit: Retrofit,
    dispatcher: CoroutineDispatcher
) : BaseApi(dispatcher) {

    private val api: BookingApi = retrofit.create()

    suspend fun post(
        requestBody: String
    ) = safeApiCall {
        api.createBooking(bodyAsMap(requestBody))
    }

    suspend fun post(
        requestBody: Map<String, Any>
    ) = safeApiCall {
        api.createBooking(requestBody)
    }

}