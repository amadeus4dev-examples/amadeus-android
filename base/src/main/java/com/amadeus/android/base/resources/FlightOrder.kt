package com.amadeus.android.base.resources

import android.os.Parcelable
import com.squareup.moshi.JsonClass
import kotlinx.android.parcel.Parcelize

/**
 * An Airline object as returned by the Airline Code LookUp API.
 * @see com.amadeus.booking.flightOrder.get
 */
@Parcelize
@JsonClass(generateAdapter = true)
data class FlightOrder internal constructor(
    val type: String? = null,
    val id: String? = null,
    val queuingOfficeId: String? = null,
    val associatedRecords: List<AssociatedRecord>? = null,
    val travelers: List<Traveler>? = null,
    val flightOffers: List<FlightOfferSearch>? = null
) : Parcelable {

    @Parcelize
    @JsonClass(generateAdapter = true)
    data class AssociatedRecord internal constructor(
        val reference: String? = null,
        val creationDateTime: String? = null,
        val originSystemCode: String? = null,
        val flightOfferId: String? = null
    ) : Parcelable

    @Parcelize
    @JsonClass(generateAdapter = true)
    data class Traveler internal constructor(
        val id: String? = null,
        val dateOfBirth: String? = null,
        val name: Name? = null,
        val contact: Contact? = null,
        val documents: List<Document>? = null
    ) : Parcelable

    @Parcelize
    @JsonClass(generateAdapter = true)
    data class Name internal constructor(
        val firstName: String? = null,
        val lastName: String? = null
    ) : Parcelable

    @Parcelize
    @JsonClass(generateAdapter = true)
    data class Contact internal constructor(
        val phones: List<Phone>? = null
    ) : Parcelable

    @Parcelize
    @JsonClass(generateAdapter = true)
    data class Document internal constructor(
        val documentType: String? = null,
        val number: String? = null,
        val expiryDate: String? = null,
        val issuanceCountry: String? = null,
        val nationality: String? = null,
        val holder: Boolean = false
    ) : Parcelable

    @Parcelize
    @JsonClass(generateAdapter = true)
    data class Phone internal constructor(
        val countryCallingCode: String? = null,
        val number: String? = null
    ) : Parcelable
}