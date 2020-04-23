package com.amadeus.android.domain.resources

import android.os.Parcelable
import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass
import kotlinx.android.parcel.Parcelize

@Parcelize
@JsonClass(generateAdapter = true)
data class TripParser(
    val type: String,
    val content: String,
    val id: String? = null,
    val self: Self? = null
) : Parcelable {

    @Parcelize
    @JsonClass(generateAdapter = true)
    data class Self(
        val href: String,
        val methods: List<MethodsEnum>? = null,
        val count: Int? = null
    ) : Parcelable {
        /**
         * Values: GET, PUT, DELETE, POST, PATCH
         */
        @JsonClass(generateAdapter = false)
        enum class MethodsEnum(val value: String) {
            @Json(name = "GET") GET("GET"),
            @Json(name = "PUT") PUT("PUT"),
            @Json(name = "DELETE") DELETE("DELETE"),
            @Json(name = "POST") POST("POST"),
            @Json(name = "PATCH") PATCH("PATCH")
        }
    }
}
