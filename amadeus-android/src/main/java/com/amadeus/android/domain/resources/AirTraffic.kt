package com.amadeus.android.domain.resources

import android.os.Parcelable
import com.squareup.moshi.JsonClass
import kotlinx.android.parcel.Parcelize

/**
 * An AirTraffic object as returned by the AirTraffic API.
 * @see Traveled.get
 */
@Parcelize
@JsonClass(generateAdapter = true)
data class AirTraffic internal constructor(
    val type: String? = null,
    val subType: String? = null,
    val destination: String? = null,
    val analytics: Analytics? = null
) : Parcelable {

    /**
     * An AirTraffic-related object as returned by the AirTraffic API.
     * @see Traveled.get
     */
    @Parcelize
    @JsonClass(generateAdapter = true)
    data class Analytics internal constructor(
        val flights: Flights? = null,
        val travelers: Travelers? = null
    ) : Parcelable {

        /**
         * An AirTraffic-related object as returned by the AirTraffic API.
         * @see Traveled.get
         */
        @Parcelize
        @JsonClass(generateAdapter = true)
        data class Flights internal constructor(
            val score: Double? = null
        ) : Parcelable

        /**
         * An AirTraffic-related object as returned by the AirTraffic API.
         * @see Traveled.get
         */
        @Parcelize
        @JsonClass(generateAdapter = true)
        data class Travelers internal constructor(
            val score: Double? = null
        ) : Parcelable
    }
}