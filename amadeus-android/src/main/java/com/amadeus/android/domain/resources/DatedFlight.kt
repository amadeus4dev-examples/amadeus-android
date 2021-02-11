package com.amadeus.android.domain.resources

import android.os.Parcelable
import com.squareup.moshi.JsonClass
import kotlinx.android.parcel.Parcelize

/**
 * An DatedFlight object as returned by the On-Demand Flight Status API.
 * @see com.amadeus.schedule.flights.get
 */
@Parcelize
@JsonClass(generateAdapter = true)
data class DatedFlight internal constructor(
    val type: String,
    val scheduledDepartureDate: String,
    val flightDesignator: FlightDesignator? = null,
    val flightPoints: List<FlightPoint>? = null,
    val segments: List<Segment>? = null,
    val legs: List<Leg>? = null
) : Parcelable {

    @Parcelize
    @JsonClass(generateAdapter = true)
    data class FlightDesignator internal constructor(
        val carrierCode: String? = null,
        val flightNumber: Int? = null,
        val operationalSuffix: String? = null
    ) : Parcelable

    @Parcelize
    @JsonClass(generateAdapter = true)
    data class FlightPoint internal constructor(
        val iataCode: String? = null,
        val departure: Departure? = null,
        val arrival: Arrival? = null
    ) : Parcelable

    @Parcelize
    @JsonClass(generateAdapter = true)
    data class Departure internal constructor(
        val timings: List<Timing>? = null,
        val terminal: List<Terminal>? = null,
        val gate: List<Gate>? = null
    ) : Parcelable

    @Parcelize
    @JsonClass(generateAdapter = true)
    data class Arrival internal constructor(
        val timings: List<Timing>? = null,
        val terminal: List<Terminal>? = null,
        val gate: List<Gate>? = null
    ) : Parcelable

    @Parcelize
    @JsonClass(generateAdapter = true)
    data class Timing internal constructor(
        val qualifier: String? = null,
        val value: String? = null,
        val delays: Delay? = null
    ) : Parcelable

    @Parcelize
    @JsonClass(generateAdapter = true)
    data class Delay internal constructor(
        val duration: String? = null
    ) : Parcelable

    @Parcelize
    @JsonClass(generateAdapter = true)
    data class Gate internal constructor(
        val mainGate: String? = null
    ) : Parcelable

    @Parcelize
    @JsonClass(generateAdapter = true)
    data class Terminal internal constructor(
        val code: String? = null
    ) : Parcelable

    @Parcelize
    @JsonClass(generateAdapter = true)
    data class Segment internal constructor(
        val boardPointIataCode: String? = null,
        val offPointIataCode: String? = null,
        val scheduledSegmentDuration: String? = null,
        val partnership: Partnership? = null
    ) : Parcelable

    @Parcelize
    @JsonClass(generateAdapter = true)
    data class Partnership internal constructor(
        val operatingFlight: FlightDesignator? = null
    ) : Parcelable

    @Parcelize
    @JsonClass(generateAdapter = true)
    data class Leg internal constructor(
        val boardPointIataCode: String? = null,
        val offPointIataCode: String? = null,
        val aircraftEquipment: AircraftEquipment? = null,
        val scheduledLegDuration: String? = null
    ) : Parcelable

    @Parcelize
    @JsonClass(generateAdapter = true)
    data class AircraftEquipment internal constructor(
        val aircraftType: String? = null
    ) : Parcelable
}