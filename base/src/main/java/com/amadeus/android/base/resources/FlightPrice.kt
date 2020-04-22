package com.amadeus.android.base.resources

import android.os.Parcelable
import com.squareup.moshi.JsonClass
import kotlinx.android.parcel.Parcelize

@Parcelize
@JsonClass(generateAdapter = true)
data class FlightPrice internal constructor(
    val type: String? = null,
    val flightOffers: List<FlightOfferSearch>? = null,
    val bookingRequirements: BookingRequirements? = null
) : Parcelable {

    @Parcelize
    @JsonClass(generateAdapter = true)
    data class BookingRequirements internal constructor(
        val invoiceAddressRequired: Boolean? = null,
        val mailingAddressRequired: Boolean? = null,
        val emailAddressRequired: Boolean? = null,
        val phoneCountryCodeRequired: Boolean? = null,
        val mobilePhoneNumberRequired: Boolean? = null,
        val phoneNumberRequired: Boolean? = null,
        val postalCodeRequired: Boolean? = null,
        val travelerRequirements: List<PassengerConditions>? = null
    ) : Parcelable

    @Parcelize
    @JsonClass(generateAdapter = true)
    data class PassengerConditions internal constructor(
        val travelerId: String? = null,
        val genderRequired: Boolean? = null,
        val documentRequired: Boolean? = null,
        val documentIssuanceCityRequired: Boolean? = null,
        val dateOfBirthRequired: Boolean? = null,
        val redressRequiredIfAny: Boolean? = null,
        val airFranceDiscountRequired: Boolean? = null,
        val spanishResidentDiscountRequired: Boolean? = null,
        val residenceRequired: Boolean? = null
    ) : Parcelable
}