package com.amadeus.android.domain.resources

import android.os.Parcelable
import com.squareup.moshi.JsonClass
import kotlinx.android.parcel.Parcelize

/**
 * An Airline object as returned by the Airline Code LookUp API.
 * @see com.amadeus.shopping.activities.get
 */
@Parcelize
@JsonClass(generateAdapter = true)
data class Activity internal constructor(
    val id: String,
    val type: String,
    val name: String,
    val shortDescription: String? = null,
    val description: String? = null,
    val geoCode: GeoCode? = null,
    val rating: String? = null,
    val bookingLink: String? = null,
    val minimumDuration: String? = null,
    val price: ElementaryPrice? = null,
    val pictures: List<String>? = null
) : Parcelable {

    /**
     * An Activity-related object as returned by the Tours and Activities API.
     * @see com.amadeus.shopping.Activity.get
     */
    @Parcelize
    @JsonClass(generateAdapter = true)
    data class GeoCode internal constructor(
        val latitude: Double = 0.0,
        val longitude: Double = 0.0
    ) : Parcelable

    @Parcelize
    @JsonClass(generateAdapter = true)
    data class ElementaryPrice internal constructor(
        val amount: String? = null,
        val currencyCode: String? = null
    ) : Parcelable
}