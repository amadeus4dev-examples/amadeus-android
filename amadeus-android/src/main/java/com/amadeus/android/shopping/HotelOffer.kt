package com.amadeus.android.shopping

import com.amadeus.android.BaseApi
import com.amadeus.android.domain.hotel.apis.ShoppingApi
import com.amadeus.android.tools.GeneratedCodeConverters
import com.squareup.moshi.Moshi
import kotlinx.coroutines.CoroutineDispatcher
import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.create

class HotelOffer internal constructor(
    retrofit: Retrofit,
    dispatcher: CoroutineDispatcher,
    private val offerId: String
) : BaseApi(dispatcher) {

    private val api: ShoppingApi = retrofit.create()

    suspend fun get(
        lang: String? = null
    ) = safeApiCall {
        api.getOfferPricing(offerId, lang)
    }

}