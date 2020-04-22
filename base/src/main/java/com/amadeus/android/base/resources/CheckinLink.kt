package com.amadeus.android.base.resources

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
    private val type: String? = null,
    private val id: String? = null,
    private val href: String? = null,
    private val channel: String? = null
) : Parcelable