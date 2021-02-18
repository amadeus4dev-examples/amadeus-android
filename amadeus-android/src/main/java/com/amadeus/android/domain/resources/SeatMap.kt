package com.amadeus.android.domain.resources

import android.os.Parcelable
import com.squareup.moshi.JsonClass
import kotlinx.android.parcel.Parcelize

/**
 * An SeatMap object as returned by the SeatMap API.
 * @see com.amadeus.booking.SeatMaps.get
 * @see com.amadeus.booking.SeatMaps.post
 */
@Parcelize
@JsonClass(generateAdapter = true)
data class SeatMap internal constructor(
    val type: String? = null,
    val flightOfferid: String? = null,
    val segmentid: String? = null,
    val carrierCode: String? = null,
    val number: String? = null,
    val aircraft: Aircraft? = null,
    val departure: Departure? = null,
    val decks: List<Deck>? = null,
    val aircraftCabinAmenities: AircraftCabinAmenities? = null,
    val availableSeatsCounters: List<AvailableSeatsCounter>? = null
) : Parcelable {

    @Parcelize
    @JsonClass(generateAdapter = true)
    data class Aircraft internal constructor(
        val code: String? = null
    ) : Parcelable

    @Parcelize
    @JsonClass(generateAdapter = true)
    data class Departure internal constructor(
        val iataCode: String? = null,
        val at: String? = null
    ) : Parcelable

    @Parcelize
    @JsonClass(generateAdapter = true)
    data class Arrival internal constructor(
        val iataCode: String? = null
    ) : Parcelable

    @Parcelize
    @JsonClass(generateAdapter = true)
    data class Deck internal constructor(
        val deckType: String? = null,
        val deckConfiguration: DeckConfiguration? = null
    ) : Parcelable

    @Parcelize
    @JsonClass(generateAdapter = true)
    data class DeckConfiguration internal constructor(
        val width: Int = 0,
        val length: Int = 0,
        val startseatRow: Int = 0,
        val endSeatRow: Int = 0,
        val startWingsRow: Int = 0,
        val endWingsRow: Int = 0,
        val startWingsX: Int = 0,
        val endWingsX: Int = 0
    ) : Parcelable

    @Parcelize
    @JsonClass(generateAdapter = true)
    data class AircraftCabinAmenities internal constructor(
        val power: AmenityPower? = null,
        val wifi: AmenityWifi? = null,
        val beverage: AmenityBeverage? = null,
        val food: AmenityFood? = null,
        val entertainment: List<AmenityEntertainment>? = null,
        val seat: AmenitySeat? = null
    ) : Parcelable

    @Parcelize
    @JsonClass(generateAdapter = true)
    data class AmenityPower internal constructor(
        val powerType: String? = null,
        val isChargeable: Boolean? = null
    ) : Parcelable

    @Parcelize
    @JsonClass(generateAdapter = true)
    data class AmenityWifi internal constructor(
        val wifiCoverage: String? = null,
        val isChargeable: Boolean? = null
    ) : Parcelable

    @Parcelize
    @JsonClass(generateAdapter = true)
    data class AmenityBeverage internal constructor(
        val beverageType: String? = null,
        val isChargeable: Boolean? = null
    ) : Parcelable

    @Parcelize
    @JsonClass(generateAdapter = true)
    data class AmenityFood internal constructor(
        val foodType: String? = null,
        val isChargeable: Boolean? = null
    ) : Parcelable

    @Parcelize
    @JsonClass(generateAdapter = true)
    data class AmenityEntertainment internal constructor(
        val entertainmentType: String? = null,
        val isChargeable: Boolean? = null
    ) : Parcelable

    @Parcelize
    @JsonClass(generateAdapter = true)
    data class AmenitySeat internal constructor(
        val legSpace: Int? = null,
        val spaceUnit: String? = null,
        val tilt: String? = null,
        val amenityType: String? = null,
        val medias: List<Media>? = null
    ) : Parcelable

    @Parcelize
    @JsonClass(generateAdapter = true)
    data class AvailableSeatsCounter internal constructor(
        val value: Int? = null,
        val travelerId: String? = null
    ) : Parcelable

    @Parcelize
    @JsonClass(generateAdapter = true)
    data class Media internal constructor(
        val title: String? = null,
        val href: String? = null,
        val mediaType: String? = null,
        val description: QualifiedFreeText? = null
    ) : Parcelable

    @Parcelize
    @JsonClass(generateAdapter = true)
    data class QualifiedFreeText internal constructor(
        val text: String? = null,
        val lang: String? = null
    ) : Parcelable
}