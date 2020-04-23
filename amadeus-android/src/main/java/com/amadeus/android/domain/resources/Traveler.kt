package com.amadeus.android.domain.resources

import android.os.Parcelable
import com.squareup.moshi.JsonClass
import kotlinx.android.parcel.Parcelize

/**
 * An Traveler object used in the body of Flight Create Orders.
 * @see com.amadeus.booking.flightOrder.get
 */
@Parcelize
@JsonClass(generateAdapter = true)
data class Traveler(
    var id: String? = null,
    var dateOfBirth: String? = null,
    var name: Name? = null,
    var contact: Contact? = null,
    var documents: List<Document>? = null
) : Parcelable {

    @Parcelize
    @JsonClass(generateAdapter = true)
    data class Name(
        var firstName: String,
        var lastName: String
    ) : Parcelable

    @Parcelize
    @JsonClass(generateAdapter = true)
    data class Contact(
        val phones: List<Phone>? = null
    ) : Parcelable

    @Parcelize
    @JsonClass(generateAdapter = true)
    data class Document(
        var documentType: String? = null,
        var number: String? = null,
        var expiryDate: String? = null,
        var issuanceCountry: String? = null,
        var nationality: String? = null,
        var holder: Boolean = false
    ) : Parcelable

    @Parcelize
    @JsonClass(generateAdapter = true)
    data class Phone(
        var countryCallingCode: String? = null,
        var number: String? = null,
        var deviceType: String? = null
    ) : Parcelable
}