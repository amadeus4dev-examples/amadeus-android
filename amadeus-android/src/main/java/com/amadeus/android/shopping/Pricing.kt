package com.amadeus.android.shopping

import com.amadeus.android.base.BaseApi
import com.amadeus.android.domain.air.apis.ShoppingApi
import com.amadeus.android.domain.air.models.GetPriceQuery
import com.amadeus.android.domain.air.tools.GeneratedCodeConverters
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
        priceFlightOffersBody: GetPriceQuery,
        include: List<String>? = null,
        forceClass: Boolean? = null
    ) = safeApiCall {
        api.quoteAirOffers(
            priceFlightOffersBody,
            include,
            forceClass
        )
    }

    @Throws(Exception::class)
    suspend fun post(
        priceFlightOffersString: String,
        include: List<String>? = null,
        forceClass: Boolean? = null
    ) = safeApiCall {
        val body = moshi.adapter(GetPriceQuery::class.java).fromJson(priceFlightOffersString)!!
        api.quoteAirOffers(
            body,
            include,
            forceClass
        )
    }
}
