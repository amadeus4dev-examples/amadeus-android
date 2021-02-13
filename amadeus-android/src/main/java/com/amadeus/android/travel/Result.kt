package com.amadeus.android.travel

import com.amadeus.android.BaseApi
import com.amadeus.android.domain.trip.apis.TripParserJobsApi
import com.amadeus.android.tools.GeneratedCodeConverters
import com.squareup.moshi.Moshi
import kotlinx.coroutines.CoroutineDispatcher
import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.create

class Result internal constructor(
    retrofit: Retrofit,
    dispatcher: CoroutineDispatcher,
    private val id: String = ""
) : BaseApi(dispatcher) {

    private val api: TripParserJobsApi = retrofit.create()

    //suspend fun get() = safeApiCall { api.getResult(id) }

}
