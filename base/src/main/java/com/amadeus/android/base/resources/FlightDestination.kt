package com.amadeus.android.base.resources

import android.os.Parcelable
import com.squareup.moshi.JsonClass
import kotlinx.android.parcel.Parcelize

/**
 * An FlightDestination object as returned by the FlightDestinations API.
 * @see com.amadeus.shopping.FlightDestinations.get
 */
@Parcelize
@JsonClass(generateAdapter = true)
data class FlightDestination internal constructor(
    val type: String? = null,
    val origin: String? = null,
    val destination: String? = null,
    val departureDate: String? = null,
    val returnDate: String? = null,
    val price: Price? = null
) : Parcelable {

    /**
     * An FlightDestination-related object as returned by the FlightDestinations API.
     * @see com.amadeus.shopping.FlightDestinations.get
     */
    @Parcelize
    @JsonClass(generateAdapter = true)
    data class Price internal constructor(
        val total: Float = 0.0f
    ) : Parcelable
}