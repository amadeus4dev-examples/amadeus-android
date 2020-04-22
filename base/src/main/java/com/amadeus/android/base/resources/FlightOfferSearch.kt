package com.amadeus.android.base.resources

import android.os.Parcelable
import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass
import kotlinx.android.parcel.Parcelize

/**
 * An Airline object as returned by the Airline Code LookUp API.
 * @see com.amadeus.shopping.flightOffersSearch.get
 */
@Parcelize
@JsonClass(generateAdapter = true)
data class FlightOfferSearch internal constructor(
    val type: String? = null,
    val id: String? = null,
    val source: String? = null,
    val instantTicketingRequired: Boolean = false,
    val nonHomogeneous: Boolean = false,
    val oneWay: Boolean = false,
    val lastTicketingDate: String? = null,
    val numberOfBookableSeats: Int = 0,
    val itineraries: List<Itinerary>? = null,
    val price: SearchPrice? = null,
    val pricingOptions: PricingOptions? = null,
    val validatingAirlineCodes: List<String>? = null,
    val travelerPricings: List<TravelerPricing>? = null
) : Parcelable {

    @Parcelize
    @JsonClass(generateAdapter = true)
    data class Itinerary internal constructor(
        val duration: String? = null,
        val segments: List<SearchSegment>? = null
    ) : Parcelable

    @Parcelize
    @JsonClass(generateAdapter = true)
    data class SearchSegment internal constructor(
        val departure: AirportInfo? = null,
        val arrival: AirportInfo? = null,
        val carrierCode: String? = null,
        val number: String? = null,
        val aircraft: Aircraft? = null,
        val duration: String? = null,
        val id: String? = null,
        val numberOfStops: Int = 0,
        val blacklistedInEU: Boolean = false,
        val co2Emissions: List<Co2Emissions>? = null
    ) : Parcelable

    @Parcelize
    @JsonClass(generateAdapter = true)
    data class Co2Emissions internal constructor(
        val weight: Int = 0,
        val weightUnit: String? = null,
        val cabin: String? = null
    ) : Parcelable

    @Parcelize
    @JsonClass(generateAdapter = true)
    data class AirportInfo internal constructor(
        val iataCode: String? = null,
        val terminal: String? = null,
        val at: String? = null
    ) : Parcelable

    @Parcelize
    @JsonClass(generateAdapter = true)
    data class Aircraft internal constructor(
        val code: String? = null
    ) : Parcelable

    @Parcelize
    @JsonClass(generateAdapter = true)
    data class SearchPrice internal constructor(
        val currency: String? = null,
        val total: Float = 0.0f,
        val base: Float = 0.0f,
        val fees: List<Fee>? = null,
        val grandTotal: Float = 0.0f
    ) : Parcelable

    @Parcelize
    @JsonClass(generateAdapter = true)
    data class Fee internal constructor(
        val amount: Float = 0.0f,
        val type: String? = null
    ) : Parcelable

    @Parcelize
    @JsonClass(generateAdapter = true)
    data class PricingOptions internal constructor(
        val includedCheckedBagsOnly: Boolean = false,
        val fareType: List<String>? = null,
        val corporateCodes: List<String>? = null,
        val refundableFare: Boolean = false,
        val noRestrictionFare: Boolean = false,
        val noPenaltyFare: Boolean = false
    ) : Parcelable

    @Parcelize
    @JsonClass(generateAdapter = true)
    data class TravelerPricing internal constructor(
        val travelerId: String? = null,
        val fareOption: String? = null,
        val travelerType: String? = null,
        val price: SearchPrice? = null,
        val fareDetailsBySegment: List<FareDetailsBySegment>? = null
    ) : Parcelable

    @Parcelize
    @JsonClass(generateAdapter = true)
    data class FareDetailsBySegment internal constructor(
        val segmentId: String? = null,
        val cabin: String? = null,
        val fareBasis: String? = null,
        @Json(name = "class") @field:Json(name = "class") val segmentClass: String? = null,
        val includedCheckedBags: IncludedCheckedBags? = null
    ) : Parcelable

    @Parcelize
    @JsonClass(generateAdapter = true)
    data class IncludedCheckedBags internal constructor(
        val weight: Int = 0,
        val weightUnit: String? = null
    ) : Parcelable
}