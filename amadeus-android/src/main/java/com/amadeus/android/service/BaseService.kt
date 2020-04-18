package com.amadeus.android.service

import okhttp3.ResponseBody
import retrofit2.http.*

interface BaseService {

    @GET
    suspend fun getByUrl(@Url url: String): ResponseBody

    @POST
    suspend fun postByUrl(@Url url: String, @Body body: String?): ResponseBody

    @DELETE
    suspend fun deleteByUrl(@Url url: String, @Body body: String?): ResponseBody

}