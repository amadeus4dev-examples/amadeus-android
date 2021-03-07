package com.amadeus.android.domain.resources

import android.os.Parcelable
import com.squareup.moshi.JsonClass
import kotlinx.android.parcel.Parcelize

/**
 * A SafePlace object as returned by the Safe Place API.
 * @see com.amadeus.safety.safety_rated_locations.SafePlace.get
 */
@Parcelize
@JsonClass(generateAdapter = true)
data class SafePlace internal constructor(
    val type: String? = null,
    val id: String? = null,
    val subType: String? = null,
    val name: String? = null,
    val geocode: GeoCode? = null,
    val safetyScores: SafetyScores? = null
) : Parcelable {

    @Parcelize
    @JsonClass(generateAdapter = true)
    data class GeoCode internal constructor(
        val latitude: Double = 0.0,
        val longitude: Double = 0.0
    ) : Parcelable

    @Parcelize
    @JsonClass(generateAdapter = true)
    data class SafetyScores internal constructor(
        val women: Int? = null,
        val theft: Int? = null,
        val physicalHarm: Int? = null,
        val politicalFreedom: Int? = null,
        val lgbtq: Int? = null,
        val medical: Int? = null,
        val overall: Int? = null
    ) : Parcelable
}