package com.amadeus.android.domain.hotel.models

import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass

/**
 * links to continue in the shopping process
 * @uri href to display the image
 * @category next Use this link to get the next hotels in the search result
 */
@JsonClass(generateAdapter = true)
data class HotelFile(
    @Json(name = "uri") @field:Json(name = "uri") var uri: String,
    @Json(name = "category") @field:Json(name = "category") var category: HotelFile.Category
) {

    @JsonClass(generateAdapter = false)
    enum class Category(val value: String) {
        @Json(name = "EXTERIOR") EXTERIOR("EXTERIOR"),
        @Json(name = "LOBBY") LOBBY("LOBBY"),
        @Json(name = "SWIMMING_POOL") SWIMMING_POOL("SWIMMING_POOL"),
        @Json(name = "RESTAURANT") RESTAURANT("RESTAURANT"),
        @Json(name = "FITNESS_CENTER") FITNESS_CENTER("FITNESS_CENTER"),
        @Json(name = "ROOM") ROOM("ROOM"),
        @Json(name = "SUITE") SUITE("SUITE"),
        @Json(name = "MEETING_ROOMS") MEETING_ROOMS("MEETING_ROOMS"),
        @Json(name = "BALLROOM") BALLROOM("BALLROOM"),
        @Json(name = "GOLF") GOLF("GOLF"),
        @Json(name = "BEACH") BEACH("BEACH"),
        @Json(name = "SPA") SPA("SPA"),
        @Json(name = "BAR") BAR("BAR"),
        @Json(name = "RECREATIONAL") RECREATIONAL("RECREATIONAL"),
        @Json(name = "LOGO") LOGO("LOGO"),
        @Json(name = "BASICS") BASICS("BASICS"),
        @Json(name = "MAP") MAP("MAP"),
        @Json(name = "PROMOTIONAL") PROMOTIONAL("PROMOTIONAL"),
        @Json(name = "HOT_NEWS") HOT_NEWS("HOT_NEWS"),
        @Json(name = "MISCELLANEOUS") MISCELLANEOUS("MISCELLANEOUS"),
        @Json(name = "ROOM_AMENITY") ROOM_AMENITY("ROOM_AMENITY"),
        @Json(name = "PROPERTY_AMENITY") PROPERTY_AMENITY("PROPERTY_AMENITY"),
        @Json(name = "BUSINESS_CENTER") BUSINESS_CENTER("BUSINESS_CENTER")
    }
}