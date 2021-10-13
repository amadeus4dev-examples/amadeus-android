package com.amadeus.android.domain.trip.apis

import com.amadeus.android.ApiResponse
import com.amadeus.android.domain.resources.GeneratedPhoto
import retrofit2.http.GET

@JvmSuppressWildcards
interface FilesApi {
    /**
     * Generate a landscape picture
     *  This operation returns a link to a generated picture. This link is valid for 24h and allows you to download the picture. All image are 512x512 pixels  ### Example Request: <pre><code> GET https://test.api.amadeus.com/v2/media/files/generated-photos?category=MOUNTAIN </code></pre>      Response: <pre><code> {   \"data\": {     \"type\": \"file\",     \"owner\": \"1A\",     \"attachmentUri\": \"http://pdt.content.amadeus.com/citypictures/2615_DFL.jpeg\",     \"description\": \"mountain landscape\",     \"fileKbSize\": 200,     \"expirationDateTime\": \"2019-11-25T22:12Z\",     \"mediaMetadata\": {       \"dimensions\": {         \"height\": 512,         \"width\": 512,         \"UOM\": \"Pixels\"       }     }   } }   </code></pre> 
     * The endpoint is owned by defaultname service owner
     * @param category select the type of landscape to be generated (required)
     */
    @GET("v2/media/files/generated-photos")
    suspend fun generateFile(
        @retrofit2.http.Query("category") category: String
    ): ApiResponse<GeneratedPhoto>
}
