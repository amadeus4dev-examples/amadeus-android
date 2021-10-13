package com.amadeus.android.shopping

import com.amadeus.android.BaseApi
import com.amadeus.android.domain.destination.apis.ActivitiesApi
import com.amadeus.android.tools.GeneratedCodeConverters
import com.squareup.moshi.Moshi
import kotlinx.coroutines.CoroutineDispatcher
import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.create

class Activity internal constructor(
    retrofit: Retrofit,
    dispatcher: CoroutineDispatcher,
    private val id: String
) : BaseApi(dispatcher) {

    private val api: ActivitiesApi = retrofit.create()

    suspend fun get() = safeApiCall {
        api.getActivity(id)
    }

}