package com.amadeus.android.shopping

import com.amadeus.android.BaseApi
import com.amadeus.android.domain.air.apis.ShoppingApi
import com.amadeus.android.tools.GeneratedCodeConverters
import com.squareup.moshi.Moshi
import kotlinx.coroutines.CoroutineDispatcher
import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.create

@Suppress("BlockingMethodInNonBlockingContext")
class Pricing internal constructor(
    baseUrl: String,
    httpClient: OkHttpClient,
    private val moshi: Moshi,
    dispatcher: CoroutineDispatcher
) : BaseApi(dispatcher) {

    override val basePath = "v2/"

    private val api: ShoppingApi = Retrofit.Builder()
        .baseUrl(baseUrl + basePath)
        .addConverterFactory(GeneratedCodeConverters.converterFactory(moshi))
        .client(httpClient)
        .build()
        .create()

    suspend fun post(
        body: String,
        include: List<String>? = null,
        forceClass: Boolean? = null
    ) = safeApiCall {
        api.quoteAirOffers(
            body,
            include,
            forceClass
        )
    }
}
