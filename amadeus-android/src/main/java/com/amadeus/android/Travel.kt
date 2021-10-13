package com.amadeus.android

import com.amadeus.android.travel.Analytics
import com.amadeus.android.travel.Predictions
import com.amadeus.android.travel.TripParserJob
import com.squareup.moshi.Moshi
import kotlinx.coroutines.CoroutineDispatcher
import okhttp3.OkHttpClient
import retrofit2.Retrofit

class Travel internal constructor(
    private val retrofit: Retrofit,
    private val dispatcher: CoroutineDispatcher
) {

    /**
     * A namespaced client for the
     * `/v1/travel/predictions` endpoints.
     */
    val predictions = Predictions(retrofit, dispatcher)

    /**
     * A namespaced client for the
     * `/v1/travel/analytics` endpoints.
     */
    val analytics = Analytics(retrofit, dispatcher)

    /**
     * A namespaced client for the
     * `/v2/travel/trip-parsed-job` endpoints.
     */
    val tripParserJob = TripParserJob(retrofit, dispatcher)

    /**
     * A namespaced client for the
     * `/v2/travel/trip-parsed-job/:id` endpoints.
     */
    fun tripParserJob(id: String) = TripParserJob(retrofit, dispatcher, id)
}