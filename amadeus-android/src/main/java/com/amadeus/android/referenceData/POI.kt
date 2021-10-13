package com.amadeus.android.referenceData

import com.amadeus.android.BaseApi
import com.amadeus.android.domain.destination.apis.POIsApi
import com.amadeus.android.tools.GeneratedCodeConverters
import com.squareup.moshi.Moshi
import kotlinx.coroutines.CoroutineDispatcher
import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.create

class POI internal constructor(
    retrofit: Retrofit,
    dispatcher: CoroutineDispatcher,
    private val id: String
) : BaseApi(dispatcher) {

    private val api: POIsApi = retrofit.create()

    suspend fun get() = safeApiCall {
        api.getPointOfInterest(id)
    }

}