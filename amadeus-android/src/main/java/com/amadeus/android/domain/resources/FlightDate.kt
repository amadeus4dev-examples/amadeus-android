package com.amadeus.android.domain.resources

import android.os.Parcelable
import com.squareup.moshi.JsonClass
import kotlinx.android.parcel.Parcelize

/**
 * An FlightDate object as returned by the FlightDates API.
 * @see com.amadeus.shopping.FlightDates.get
 */
@Parcelize
@JsonClass(generateAdapter = true)
data class FlightDate internal constructor(
    val type: String? = null,
    val origin: String? = null,
    val destination: String? = null,
    val departureDate: String? = null,
    val returnDate: String? = null,
    val price: Price? = null
) : Parcelable {

    /**
     * An FlightDate-related object as returned by the FlightDates API.
     * @see com.amadeus.shopping.FlightDates.get
     */
    @Parcelize
    @JsonClass(generateAdapter = true)
    data class Price internal constructor(
        val total: Float = 0.0f
    ) : Parcelable
}