package com.amadeus.android.referenceData

import kotlinx.coroutines.CoroutineDispatcher
import retrofit2.Retrofit

class Urls internal constructor(
    retrofit: Retrofit,
    dispatcher: CoroutineDispatcher
) {

    /**
     * A namespaced client for the
     * `/v2/reference-data/urls/checkin-links` endpoints.
     */
    val checkinLinks = CheckinLinks(retrofit, dispatcher)

}