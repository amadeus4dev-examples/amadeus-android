package com.amadeus.android.base.resources

import android.os.Parcelable
import com.squareup.moshi.JsonClass
import kotlinx.android.parcel.Parcelize

/**
 * An GeneratedPhoto object as returned by the AI-Generated Photos API.
 * @see Traveled.get
 */
@Parcelize
@JsonClass(generateAdapter = true)
data class GeneratedPhoto internal constructor(
    val type: String? = null,
    val owner: String? = null,
    val attachmentUri: String? = null,
    val description: String? = null,
    val fileKbSize: String? = null,
    val expirationDateTime: String? = null,
    val mediaMetadata: MediaMetadata? = null
) : Parcelable {

    /**
     * A MediaMetadata-related object as returned by the AI-Generated Photos API.
     * @see GeneratedPhotos.get
     */
    @Parcelize
    @JsonClass(generateAdapter = true)
    data class MediaMetadata internal constructor(
        val dimensions: Dimension? = null
    ) : Parcelable {

        /**
         * A MediaMetadata-related object as returned by the AI-Generated Photos API.
         * @see GeneratedPhotos.get
         */
        @Parcelize
        @JsonClass(generateAdapter = true)
        data class Dimension internal constructor(
            val height: String? = null,
            val width: String? = null
        ) : Parcelable
    }
}