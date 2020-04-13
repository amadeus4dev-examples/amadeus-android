/**
 * NOTE: This class is auto generated by the Swagger Gradle Codegen for the following API: Flight Offers Price
 *
 * More info on this tool is available on https://github.com/Yelp/swagger-gradle-codegen
 */

package com.amadeus.android.domain.air.models

import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass

/**
 * @property brand
 * @property binNumber The first 6 digits of the credit card
 * @property flightOfferIds Id of the flightOffers to pay
 */
@JsonClass(generateAdapter = true)
data class Payment(
    @Json(name = "brand") @field:Json(name = "brand") var brand: PaymentBrand? = null,
    @Json(name = "binNumber") @field:Json(name = "binNumber") var binNumber: Int? = null,
    @Json(name = "flightOfferIds") @field:Json(name = "flightOfferIds") var flightOfferIds: List<String>? = null
)