package com.amadeus.android.base.resources

import android.os.Parcelable
import com.squareup.moshi.JsonClass
import kotlinx.android.parcel.Parcelize


/**
 * An Period object as returned by the Prediction APIs.
 *
 * @see com.amadeus.travel.predictions.TripPurpose.get
 */
@Parcelize
@JsonClass(generateAdapter = true)
data class Prediction internal constructor(
    val type: String? = null,
    val subType: String? = null,
    val id: String? = null,
    val result: String? = null,
    val probability: String? = null
) : Parcelable
