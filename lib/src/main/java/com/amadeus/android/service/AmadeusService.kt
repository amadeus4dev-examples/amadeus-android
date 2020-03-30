package com.amadeus.android.service

import com.amadeus.android.model.AccessToken
import retrofit2.Call
import retrofit2.http.Field
import retrofit2.http.FormUrlEncoded
import retrofit2.http.POST

internal interface AmadeusService {

    /***
     * Token
     */
    @FormUrlEncoded
    @POST("v1/security/oauth2/token")
    fun getAccessToken(
        @Field("client_id") clientId: String,
        @Field("client_secret") clientSecret: String,
        @Field("grant_type") grant_type: String = "client_credentials"
    ): Call<AccessToken>

}