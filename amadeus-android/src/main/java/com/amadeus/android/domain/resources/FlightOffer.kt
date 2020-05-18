package com.amadeus.android.domain.resources

import android.os.Parcelable
import com.squareup.moshi.JsonClass
import kotlinx.android.parcel.Parcelize

/**
 * An FlightOffer object as returned by the FlightOffers API.
 * @see com.amadeus.shopping.FlightOffers.get
 */
@Parcelize
@JsonClass(generateAdapter = true)
data class FlightOffer internal constructor(
    val type: String? = null,
    val id: String? = null,
    val offerItems: List<OfferItem>? = null,
    val choiceProbability: String? = null
) : Parcelable {

    /**
     * An FlightOffer-related object as returned by the FlightOffers API.
     * @see com.amadeus.shopping.FlightOffers.get
     */
    @Parcelize
    @JsonClass(generateAdapter = true)
    data class OfferItem internal constructor(
        val services: List<Service>? = null,
        val price: Price? = null,
        val pricePerAdult: Price? = null,
        val pricePerInfant: Price? = null,
        val pricePerChild: Price? = null,
        val pricePerSenior: Price? = null
    ) : Parcelable

    /**
     * An FlightOffer-related object as returned by the FlightOffers API.
     * @see com.amadeus.shopping.FlightOffers.get
     */
    @Parcelize
    @JsonClass(generateAdapter = true)
    data class Service internal constructor(
        val segments: List<Segment>? = null
    ) : Parcelable

    /**
     * An FlightOffer-related object as returned by the FlightOffers API.
     * @see com.amadeus.shopping.FlightOffers.get
     */
    @Parcelize
    @JsonClass(generateAdapter = true)
    data class Segment internal constructor(
        val flightSegment: FlightSegment? = null,
        val pricingDetailPerAdult: PricingDetail? = null,
        val pricingDetailPerInfant: PricingDetail? = null,
        val pricingDetailPerChild: PricingDetail? = null,
        val pricingDetailPerSenior: PricingDetail? = null
    ) : Parcelable

    /**
     * An FlightOffer-related object as returned by the FlightOffers API.
     * @see com.amadeus.shopping.FlightOffers.get
     */
    @Parcelize
    @JsonClass(generateAdapter = true)
    data class FlightSegment internal constructor(
        val departure: FlightEndPoint? = null,
        val arrival: FlightEndPoint? = null,
        val carrierCode: String? = null,
        val number: String? = null,
        val operating: OperatingFlight? = null,
        val duration: String? = null,
        val stops: List<FlightStop>? = null
    ) : Parcelable

    /**
     * An FlightOffer-related object as returned by the FlightOffers API.
     * @see com.amadeus.shopping.FlightOffers.get
     */
    @Parcelize
    @JsonClass(generateAdapter = true)
    data class FlightEndPoint internal constructor(
        val iataCode: String? = null,
        val terminal: String? = null,
        val at: String? = null
    ) : Parcelable

    /**
     * An FlightOffer-related object as returned by the FlightOffers API.
     * @see com.amadeus.shopping.FlightOffers.get
     */
    @Parcelize
    @JsonClass(generateAdapter = true)
    data class OperatingFlight internal constructor(
        val carrierCode: String? = null,
        val number: String? = null
    ) : Parcelable

    /**
     * An FlightOffer-related object as returned by the FlightOffers API.
     * @see com.amadeus.shopping.FlightOffers.get
     */
    @Parcelize
    @JsonClass(generateAdapter = true)
    data class FlightStop internal constructor(
        val iataCode: String? = null,
        val newAircraft: AircraftEquipment? = null,
        val duration: String? = null,
        val arrivalAt: String? = null,
        val departureAt: String? = null
    ) : Parcelable

    /**
     * An FlightOffer-related object as returned by the FlightOffers API.
     * @see com.amadeus.shopping.FlightOffers.get
     */
    @Parcelize
    @JsonClass(generateAdapter = true)
    data class AircraftEquipment internal constructor(
        val code: String? = null
    ) : Parcelable

    /**
     * An FlightOffer-related object as returned by the FlightOffers API.
     * @see com.amadeus.shopping.FlightOffers.get
     */
    @Parcelize
    @JsonClass(generateAdapter = true)
    data class Price internal constructor(
        val total: Double = 0.0,
        val totalTaxes: Double = 0.0
    ) : Parcelable

    /**
     * An FlightOffer-related object as returned by the FlightOffers API.
     * @see com.amadeus.shopping.FlightOffers.get
     */
    @Parcelize
    @JsonClass(generateAdapter = true)
    data class PricingDetail internal constructor(
        val travelClass: String? = null,
        val fareClass: String? = null,
        val availability: Int = 0,
        val fareBasis: String? = null
    ) : Parcelable
}