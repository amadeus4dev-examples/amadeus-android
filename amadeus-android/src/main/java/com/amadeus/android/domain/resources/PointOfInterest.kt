package com.amadeus.android.domain.resources

import android.os.Parcelable
import com.squareup.moshi.JsonClass
import kotlinx.android.parcel.Parcelize

/**
 * A PointOfInterest object as returned by the Location API.
 * @see com.amadeus.referenceData.locations.PointOfInterest.get
 */
@Parcelize
@JsonClass(generateAdapter = true)
data class PointOfInterest internal constructor(
    val type: String? = null,
    val subType: String? = null,
    val name: String? = null,
    val geoCode: GeoCode? = null,
    val category: String? = null,
    val tags: List<String>? = null
) : Parcelable {

    /**
     * An PointOfInterest-related object as returned by the PointOfInterest API.
     * @see com.amadeus.referenceData.locations.PointOfInterest.get
     */
    @Parcelize
    @JsonClass(generateAdapter = true)
    data class GeoCode internal constructor(
        val latitude: Float = 0.0f,
        val longitude: Float = 0.0f
    ) : Parcelable
}