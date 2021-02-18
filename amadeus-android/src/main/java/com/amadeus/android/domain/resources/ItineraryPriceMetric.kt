package com.amadeus.android.domain.resources

import android.os.Parcelable
import com.squareup.moshi.JsonClass
import kotlinx.android.parcel.Parcelize

/**
 * An ItineraryPriceMetric object as returned by the Flight Price Analysis API.
 * @see ItineraryPriceMetrics.get
 */
@Parcelize
@JsonClass(generateAdapter = true)
data class ItineraryPriceMetric internal constructor(
    val type: String? = null,
    val departureDate: String? = null,
    val transportType: String? = null,
    val currencyCode: String? = null,
    val origin: Location? = null,
    val destination: Location? = null,
    val priceMetrics: List<PriceMetrics>? = null,
    val oneWay: Boolean? = null
) : Parcelable {

    @Parcelize
    @JsonClass(generateAdapter = true)
    data class Location internal constructor(
        val iataCode: String? = null
    ) : Parcelable

    @Parcelize
    @JsonClass(generateAdapter = true)
    data class PriceMetrics internal constructor(
        val amount: String? = null,
        val quartileRanking: String? = null
    ) : Parcelable
}