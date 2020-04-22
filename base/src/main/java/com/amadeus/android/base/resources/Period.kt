package com.amadeus.android.base.resources

import android.os.Parcelable
import com.squareup.moshi.JsonClass
import kotlinx.android.parcel.Parcelize

/**
 * An Period object as returned by the BusiestPeriod API.
 * @see Traveled.get
 */
@Parcelize
@JsonClass(generateAdapter = true)
data class Period internal constructor(
    val type: String? = null,
    val period: String? = null,
    val analytics: Analytics? = null
) : Parcelable {

    /**
     * An Period-related object as returned by the Busiest Period API.
     * @see Traveled.get
     */
    @Parcelize
    @JsonClass(generateAdapter = true)
    data class Analytics internal constructor(
        val travelers: Travelers? = null
    ) : Parcelable {

        /**
         * An Period-related object as returned by the BusiestPeriod API.
         * @see Traveled.get
         */
        @Parcelize
        @JsonClass(generateAdapter = true)
        data class Travelers internal constructor(
            val score: Double? = null
        ) : Parcelable
    }
}