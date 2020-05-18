package com.amadeus.android.domain.resources

import android.os.Parcelable
import com.squareup.moshi.JsonClass
import kotlinx.android.parcel.Parcelize

/**
 * An Location object as returned by the Locaion API.
 * @see com.amadeus.referenceData.Location.get
 */
@Parcelize
@JsonClass(generateAdapter = true)
data class Location internal constructor(
    val type: String? = null,
    val subType: String? = null,
    val name: String? = null,
    val detailedName: String? = null,
    val timeZoneOffset: String? = null,
    val iataCode: String? = null,
    val geoCode: GeoCode? = null,
    val address: Address? = null,
    val distance: Distance? = null,
    val analytics: Analytics? = null,
    val relevance: Double? = null
) : Parcelable {

    /**
     * An Location-related object as returned by the Location API.
     * @see com.amadeus.referenceData.Location.get
     */
    @Parcelize
    @JsonClass(generateAdapter = true)
    data class GeoCode internal constructor(
        val latitude: Double = 0.0,
        val longitude: Double = 0.0
    ) : Parcelable

    /**
     * An Location-related object as returned by the Location API.
     * @see com.amadeus.referenceData.Location.get
     */
    @Parcelize
    @JsonClass(generateAdapter = true)
    data class Address internal constructor(
        val cityName: String? = null,
        val cityCode: String? = null,
        val countryName: String? = null,
        val countryCode: String? = null,
        val regionCode: String? = null
    ) : Parcelable

    /**
     * An Location-related object as returned by the Location API.
     * @see com.amadeus.referenceData.Location.get
     */
    @Parcelize
    @JsonClass(generateAdapter = true)
    data class Distance internal constructor(
        val value: Double? = null,
        val unit: String? = null
    ) : Parcelable

    /**
     * An Location-related object as returned by the Location API.
     * @see com.amadeus.referenceData.Location.get
     */
    @Parcelize
    @JsonClass(generateAdapter = true)
    data class Analytics internal constructor(
        val flights: Flights? = null,
        val travelers: Travelers? = null
    ) : Parcelable {

        /**
         * An Location-related object as returned by the Location API.
         * @see com.amadeus.referenceData.Location.get
         */
        @Parcelize
        @JsonClass(generateAdapter = true)
        data class Flights internal constructor(
            val score: Double? = null
        ) : Parcelable

        /**
         * An Location-related object as returned by the Location API.
         * @see com.amadeus.referenceData.Location.get
         */
        @Parcelize
        @JsonClass(generateAdapter = true)
        data class Travelers internal constructor(
            val score: Double? = null
        ) : Parcelable
    }
}