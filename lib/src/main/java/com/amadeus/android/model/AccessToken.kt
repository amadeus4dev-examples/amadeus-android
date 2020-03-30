package com.amadeus.android.model

import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
data class AccessToken internal constructor(
    @Json(name = "access_token") val accessToken: String = "",
    @Json(name = "application_name") val applicationName: String = "",
    @Json(name = "client_id") val clientId: String = "",
    @Json(name = "expires_in") val expiresIn: Int = 0,
    val scope: String = "",
    val state: String = "",
    @Json(name = "token_type") val tokenType: String = "",
    val type: String = "",
    val username: String = "",
    val authorization: String = "$tokenType $accessToken"
)