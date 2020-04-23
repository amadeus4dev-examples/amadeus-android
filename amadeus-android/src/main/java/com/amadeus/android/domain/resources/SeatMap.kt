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
    val decks: List<Deck>? = null
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
}