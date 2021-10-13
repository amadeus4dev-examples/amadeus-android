package com.amadeus.android.referenceData

import androidx.annotation.VisibleForTesting
import com.amadeus.android.BaseApi
import com.amadeus.android.domain.air.apis.CheckinLinksApi
import com.amadeus.android.tools.GeneratedCodeConverters
import com.squareup.moshi.Moshi
import kotlinx.coroutines.CoroutineDispatcher
import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.create

class CheckinLinks internal constructor(
    retrofit: Retrofit,
    dispatcher: CoroutineDispatcher
) : BaseApi(dispatcher) {

    @VisibleForTesting
    val api: CheckinLinksApi = retrofit.create()

    suspend fun get(airlineCode: String, language: String? = null) = safeApiCall {
        api.getCheckinURLs(airlineCode, language)
    }
}