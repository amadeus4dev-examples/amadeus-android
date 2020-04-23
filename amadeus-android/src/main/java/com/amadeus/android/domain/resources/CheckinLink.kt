package com.amadeus.android.domain.resources

import android.os.Parcelable
import com.squareup.moshi.JsonClass
import kotlinx.android.parcel.Parcelize

/**
 * An CheckinLink object as returned by the CheckinLink API.
 * @see com.amadeus.referenceData.urls.CheckinLinks.get
 */
@Parcelize
@JsonClass(generateAdapter = true)
data class CheckinLink internal constructor(
    val type: String? = null,
    val id: String? = null,
    val href: String? = null,
    val channel: String? = null
) : Parcelable