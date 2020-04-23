package com.amadeus.android.shopping

import com.amadeus.android.BaseApi
import com.amadeus.android.domain.hotel.apis.ShoppingApi
import com.amadeus.android.tools.CSV
import com.amadeus.android.tools.GeneratedCodeConverters
import kotlinx.coroutines.CoroutineDispatcher
import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.create

class HotelOffersByHotel internal constructor(
    baseUrl: String,
    httpClient: OkHttpClient,
    dispatcher: CoroutineDispatcher
) : BaseApi(dispatcher) {

    override val basePath = "v2/"

    private val api: ShoppingApi = Retrofit.Builder()
        .baseUrl(baseUrl + basePath)
        .addConverterFactory(GeneratedCodeConverters.converterFactory())
        .client(httpClient)
        .build()
        .create()

    suspend fun get(
        hotelId: String,
        checkInDate: String? = null,
        checkOutDate: String? = null,
        adults: Int? = null,
        @CSV childAges: List<Int>? = null,
        @CSV rateCodes: List<String>? = null,
        roomQuantity: Int? = null,
        currency: String? = null,
        paymentPolicy: String? = null,
        boardType: String? = null,
        view: String? = null,
        lang: String? = null
    ) = safeApiCall {
        api.getSingleHotelOffers(
            hotelId,
            checkInDate,
            checkOutDate,
            adults,
            childAges,
            rateCodes,
            roomQuantity,
            currency,
            paymentPolicy,
            boardType,
            view,
            lang
        )
    }

}