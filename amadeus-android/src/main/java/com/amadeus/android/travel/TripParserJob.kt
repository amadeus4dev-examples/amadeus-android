package com.amadeus.android.travel

import com.amadeus.android.base.BaseApi
import com.amadeus.android.domain.trip.apis.TripParserJobsApi
import com.amadeus.android.domain.trip.models.BodyTripParserJob
import com.amadeus.android.domain.trip.tools.GeneratedCodeConverters
import kotlinx.coroutines.CoroutineDispatcher
import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.create

class TripParserJob internal constructor(
    baseUrl: String,
    httpClient: OkHttpClient,
    dispatcher: CoroutineDispatcher,
    private val id: String = ""
) : BaseApi(dispatcher) {

    /**
     * A namespaced client for the
     * `/v2/travel/trip-parsed-job/result` endpoints.
     */
    val result = Result(baseUrl, httpClient, dispatcher, id)

    override val basePath = "v2/"

    private val api: TripParserJobsApi = Retrofit.Builder()
        .baseUrl(baseUrl + basePath)
        .addConverterFactory(GeneratedCodeConverters.converterFactory())
        .client(httpClient)
        .build()
        .create()

    suspend fun get() = safeApiCall { api.getTripParserJob(id) }

    suspend fun get(body: BodyTripParserJob) = safeApiCall { api.createTripParserJob(body) }

}
