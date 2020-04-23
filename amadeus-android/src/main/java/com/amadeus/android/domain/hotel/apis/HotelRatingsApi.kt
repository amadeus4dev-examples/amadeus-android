package com.amadeus.android.domain.hotel.apis

import com.amadeus.android.ApiResponse
import com.amadeus.android.domain.resources.HotelSentiment
import com.amadeus.android.tools.CSV
import retrofit2.http.GET

@JvmSuppressWildcards
interface HotelRatingsApi {
    /**
     * Get sentiments by Amadeus Hotel Ids
     * Given a list of Amadeus Hotel Ids, retrieve the information that comes from Sentiment Analysis of its reviews. Not all the hotels have scores for all the categories.  Get sentiments scores for 3 hotels, 2 of which are found and 1 is not found. <pre><code> GET https://test.api.amadeus.com/v2/e-reputation/hotel-sentiments?hotelIds=TELONMFS,ADNYCCTB,XXXYYY01 </code></pre>    ### Response example <pre><code> { \"data\": [  {     \"type\": \"hotelSentiment\",     \"numberOfReviews\": 218,     \"numberOfRatings\": 278,    \"hotelId\": \"ADNYCCTB\",     \"overallRating\": 93,     \"sentiments\": {       \"sleepQuality\": 87,       \"service\": 98,       \"facilities\": 90,       \"roomComforts\": 92,       \"valueForMoney\": 87,       \"catering\": 89,       \"location\": 98,       \"pointsOfInterest\": 91,       \"staff\": 100     }   },   {     \"type\": \"hotelSentiment\",     \"numberOfReviews\": 2667,     \"numberOfRatings\": 2666,     \"hotelId\": \"TELONMFS\",     \"overallRating\": 81,     \"sentiments\": {       \"sleepQuality\": 78,       \"service\": 80,       \"facilities\": 75,       \"roomComforts\": 87,       \"valueForMoney\": 75,       \"catering\": 81,      \"location\": 89,       \"internet\": 72,       \"pointsOfInterest\": 81,       \"staff\": 89     }   } ], \"meta\": {    \"count\": 1,      \"links\": {         \"self\": \"https://test.api.amadeus.com/v2/e-reputation/hotel-sentiments?hotelIds=ADNYCCTB,TELONMFS,XXXYYY01\"     } }, \"warnings\":  [    {       \"code\": 913,       \"title\": \"PROPERTIES NOT FOUND\",       \"detail\": \"Some of the requested properties were not found in our database.\",       \"source\": {         \"parameter\": \"hotelIds\",         \"pointer\": \"XXXYYY01\"       }     }   ] }</code></pre>
     * The endpoint is owned by defaultname service owner
     * @param hotelIds Comma separeted list of Amadues Hotel Ids (Max 3). Amadeus Hotel Ids are found in the Hotel Search response (parameter name is &#39;hotelId&#39;). (required)
     */
    @GET("e-reputation/hotel-sentiments")
    suspend fun getSentimentsByHotelIds(
        @retrofit2.http.Query("hotelIds") @CSV hotelIds: List<String>
    ): ApiResponse<List<HotelSentiment>>
}
