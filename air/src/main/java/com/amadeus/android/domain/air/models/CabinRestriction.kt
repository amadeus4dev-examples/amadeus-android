/**
 * NOTE: This class is auto generated by the Swagger Gradle Codegen for the following API: Flight Offers Search
 *
 * More info on this tool is available on https://github.com/Yelp/swagger-gradle-codegen
 */

package com.amadeus.android.domain.air.models

import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass

/**
 * @property cabin
 * @property coverage
 * @property originDestinationIds The list of originDestination identifiers for which the cabinRestriction applies
 */
@JsonClass(generateAdapter = true)
data class CabinRestriction(
    @Json(name = "cabin") @field:Json(name = "cabin") var cabin: TravelClass? = null,
    @Json(name = "coverage") @field:Json(name = "coverage") var coverage: Coverage? = null,
    @Json(name = "originDestinationIds") @field:Json(name = "originDestinationIds") var originDestinationIds: List<String>? = null
)