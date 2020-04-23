package com.amadeus.android.domain.resources

import android.os.Parcelable
import com.squareup.moshi.JsonClass
import kotlinx.android.parcel.Parcelize

/**
 * An HotelSentiment object as returned by the Hotel Ratings API.
 * @see HotelSentiments.get
 */
@Parcelize
@JsonClass(generateAdapter = true)
data class HotelSentiment internal constructor(
    val hotelId: String? = null,
    val type: String? = null,
    val overallRating: Int = 0,
    val numberOfReviews: Int = 0,
    val sentiments: Sentiment? = null
) : Parcelable {

    @Parcelize
    @JsonClass(generateAdapter = true)
    data class Sentiment internal constructor(
        val staff: Int = 0,
        val location: Int = 0,
        val service: Int = 0,
        val roomComforts: Int = 0,
        val sleepQuality: Int = 0,
        val swimmingPool: Int = 0,
        val valueForMoney: Int = 0,
        val facilities: Int = 0,
        val catering: Int = 0,
        val pointsOfInterest: Int = 0
    ) : Parcelable
}