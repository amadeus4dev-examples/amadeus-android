package com.amadeus.android.domain.resources

import android.os.Parcelable
import com.squareup.moshi.JsonClass
import kotlinx.android.parcel.Parcelize

/**
 * An Airline object as returned by the Airline Code LookUp API.
 */
@Parcelize
@JsonClass(generateAdapter = true)
data class Airline internal constructor(
    val type: String? = null,
    val iataCode: String? = null,
    val icaoCode: String? = null,
    val businessName: String? = null,
    val commonName: String? = null
) : Parcelable