/**
 * NOTE: This class is auto generated by the Swagger Gradle Codegen for the following API: Seatmap Display
 *
 * More info on this tool is available on https://github.com/Yelp/swagger-gradle-codegen
 */

package com.amadeus.android.domain.air.models

import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass

/**
 * @property x coordinate for the Length
 * @property y coordinate for the Width
 */
@JsonClass(generateAdapter = true)
data class Coordinates(
    @Json(name = "x") @field:Json(name = "x") var x: Int? = null,
    @Json(name = "y") @field:Json(name = "y") var y: Int? = null
)