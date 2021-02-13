package com.amadeus.android.shopping

import com.amadeus.android.BaseApi
import com.amadeus.android.domain.hotel.apis.ShoppingApi
import com.amadeus.android.tools.CSV
import com.amadeus.android.tools.GeneratedCodeConverters
import com.squareup.moshi.Moshi
import kotlinx.coroutines.CoroutineDispatcher
import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.create

class HotelOffers internal constructor(
    retrofit: Retrofit,
    dispatcher: CoroutineDispatcher
) : BaseApi(dispatcher) {

    private val api: ShoppingApi = retrofit.create()

    suspend fun get(
        cityCode: String? = null,
        latitude: Double? = null,
        longitude: Double? = null,
        @CSV hotelIds: List<String>? = null,
        checkInDate: String? = null,
        checkOutDate: String? = null,
        roomQuantity: Int? = null,
        adults: Int? = null,
        @CSV childAges: List<Int>? = null,
        radius: Int? = null,
        radiusUnit: String? = null,
        hotelName: String? = null,
        @CSV chains: List<String>? = null,
        @CSV rateCodes: List<String>? = null,
        @CSV amenities: List<String>? = null,
        @CSV ratings: List<Int>? = null,
        priceRange: String? = null,
        currency: String? = null,
        paymentPolicy: String? = null,
        boardType: String? = null,
        includeClosed: Boolean? = null,
        bestRateOnly: Boolean? = null,
        view: String? = null,
        sort: String? = null,
        pageLimit: Int? = null,
        pageOffset: String? = null,
        lang: String? = null
    ) = safeApiCall {
        api.getMultiHotelOffers(
            cityCode,
            latitude,
            longitude,
            hotelIds,
            checkInDate,
            checkOutDate,
            roomQuantity,
            adults,
            childAges,
            radius,
            radiusUnit,
            hotelName,
            chains,
            rateCodes,
            amenities,
            ratings,
            priceRange,
            currency,
            paymentPolicy,
            boardType,
            includeClosed,
            bestRateOnly,
            view,
            sort,
            pageLimit,
            pageOffset,
            lang
        )
    }

}