package com.amadeus.android.safety

import com.amadeus.android.BaseApi
import com.amadeus.android.domain.destination.apis.SafePlaceApi
import com.amadeus.android.tools.GeneratedCodeConverters
import com.squareup.moshi.Moshi
import kotlinx.coroutines.CoroutineDispatcher
import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.create

class SafetyRatedLocation internal constructor(
    retrofit: Retrofit,
    dispatcher: CoroutineDispatcher,
    private val id: String
) : BaseApi(dispatcher) {

    private val api: SafePlaceApi = retrofit.create()

    suspend fun get() = safeApiCall {
        api.getSafetyRatedLocation(id)
    }

}