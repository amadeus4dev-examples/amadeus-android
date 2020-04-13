/**
 * NOTE: This class is auto generated by the Swagger Gradle Codegen for the following API: Hotel
 *
 * More info on this tool is available on https://github.com/Yelp/swagger-gradle-codegen
 */

package com.amadeus.android.domain.hotel.models

import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass

/**
 * @property adults number of adult guests (1-9) per room
 * @property childAges Comma separated list of ages of each child at the time of check-out from the hotel. If several children have the same age, the ages will be repeated.
 */
@JsonClass(generateAdapter = true)
data class Guests(
    @Json(name = "adults") @field:Json(name = "adults") var adults: Int? = null,
    @Json(name = "childAges") @field:Json(name = "childAges") var childAges: List<Int>? = null
)