package com.amadeus.android.ereputation

import com.amadeus.android.BaseApi
import com.amadeus.android.domain.hotel.apis.HotelRatingsApi
import com.amadeus.android.tools.GeneratedCodeConverters
import com.squareup.moshi.Moshi
import kotlinx.coroutines.CoroutineDispatcher
import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.create

class HotelSentiments internal constructor(
    retrofit: Retrofit,
    dispatcher: CoroutineDispatcher
) : BaseApi(dispatcher) {

    private val api: HotelRatingsApi = retrofit.create()

    suspend fun get(
        ids: List<String>
    ) = safeApiCall {
        api.getSentimentsByHotelIds(ids)
    }

}