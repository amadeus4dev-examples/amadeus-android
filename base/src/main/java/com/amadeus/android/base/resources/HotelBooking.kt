package com.amadeus.android.base.resources

import android.os.Parcelable
import com.squareup.moshi.JsonClass
import kotlinx.android.parcel.Parcelize

/**
 * An HotelBooking object as returned by the Hotel Booking API.
 * @see com.amadeus.booking.HotelBookings.get
 */
@Parcelize
@JsonClass(generateAdapter = true)
data class HotelBooking internal constructor(
    val type: String? = null,
    val id: String? = null,
    val providerConfirmationId: String? = null,
    val associatedRecords: List<AssociatedRecord>? = null
) : Parcelable {

    @Parcelize
    @JsonClass(generateAdapter = true)
    data class AssociatedRecord internal constructor(
        val reference: String? = null,
        val originSystemCode: String? = null
    ) : Parcelable
}