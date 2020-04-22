package com.amadeus.android.base.resources

import android.os.Parcelable
import com.squareup.moshi.JsonClass
import kotlinx.android.parcel.Parcelize

/**
 * An FlightPayment object as returned by the Flight Offers Price API.
 * @see amadeus.shopping.flightOffersSearch.pricing.post
 */
@Parcelize
@JsonClass(generateAdapter = true)
data class FlightPayment internal constructor(
    val brand: String? = null,
    val binNumber: Int? = null,
    val flightOfferIds: List<String>? = null
) : Parcelable