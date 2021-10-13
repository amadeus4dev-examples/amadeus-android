package com.amadeus.android.domain.air.apis

import com.amadeus.android.ApiResponse
import com.amadeus.android.domain.resources.CheckinLink
import retrofit2.http.GET

@JvmSuppressWildcards
interface CheckinLinksApi {
    /**
     * Lists Check-in URLs.
     * This operation allows retrieving a list of check-in urls based on the provider code and the language.  ### Request example: What is the URL to my online check-in? <pre><code>  GET https://test.api.amadeus.com/v2/reference-data/urls/checkin-links?airlineCode=BA </code></pre>  ### Response Examples <pre><code> {     \"warnings\": [         {             \"status\": 200,             \"code\": 10151,             \"title\": \"DEFAULT LANGUAGE USED\",             \"detail\": \"Language not available for this airline, default language applied\",             \"source\": {                 \"parameter\": \"language\"             }         }     ],     \"meta\": {         \"count\": 1,         \"links\": {             \"self\": \"http://test.api.amadeus.com/v2/reference-data/urls/checkin-links?airlineCode=BA\"         }     },     \"data\": [         {             \"type\": \"checkin-link\",             \"id\": \"BAEN-GBAll\",             \"href\": \"https://www.britishairways.com/travel/managebooking/public/en_ch?&bookingRef={PNR}&lastname={LAST}\",             \"channel\": \"All\",             \"parameters\": {                 \"LAST\": {                     \"description\": \"Passenger Last Name\",                     \"type\": \"string\"                 },                 \"PNR\": {                     \"description\": \"Record Locator\",                     \"type\": \"string\",                     \"format\": \"([a-zA-Z]|[0-9]){6}\"                 }             }         }     ] }     </code></pre>
     * The endpoint is owned by defaultname service owner
     * @param airlineCode Airline code following IATA or ICAO standard - e.g. 1X; AF or ESY  [IATA table codes](http://www.iata.org/publications/Pages/code-search.aspx)  [ICAO airlines table codes](https://en.wikipedia.org/wiki/List_of_airline_codes)  (required)
     * @param language Check-in page language with one of the following patterns &#39;languageCode&#39; (e.g. EN) or &#39;languageCode-IATAcountryCode&#39; (e.g. en-GB).   Default value is **en-GB** (used when required language is not available or when no value is specified).  (optional)
     */
    @GET("v2/reference-data/urls/checkin-links")
    suspend fun getCheckinURLs(
        @retrofit2.http.Query("airlineCode") airlineCode: String,
        @retrofit2.http.Query("language") language: String?
    ): ApiResponse<List<CheckinLink>>
}
