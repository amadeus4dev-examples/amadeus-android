package com.amadeus.android.domain.trip.apis

import com.amadeus.android.ApiResponse
import com.amadeus.android.domain.resources.Prediction
import retrofit2.http.GET

@JvmSuppressWildcards
interface TripPurposePredictionApi {
    /**
     * Returns the forecast purpose of a trip
     * Returns the forecast purpose of a trip: Business or Leisure  ### Request example: <pre><code>  GET https://test.api.amadeus.com/v1/travel/predictions/trip-purpose?originLocationCode=NYC&destinationLocationCode=MAD&departureDate=2020-08-01&returnDate=2020-08-12&searchDate=2020-06-11 </code></pre>   ### Response example: <pre><code> {   \"meta\": {         \"links\": {             \"self\": \"https://test.api.amadeus.com/v1/travel/predictions/trip-purpose?originLocationCode=NYC&destinationLocationCode=MAD&departureDate=2020-08-01&returnDate=2020-08-12&searchDate=2020-06-11\"         }     },   \"data\": {       \"id\": \"NCECDG25012019\",       \"type\": \"prediction\",       \"subtype\": \"trip-purpose\",       \"result\": \"BUSINESS\",       \"probability\" : 0.7891235     } } </code></pre> 
     * The endpoint is owned by defaultname service owner
     * @param originLocationCode city/airport [IATA code](http://www.iata.org/publications/Pages/code-search.aspx) from which the traveler will depart, e.g. BOS for Boston (required)
     * @param destinationLocationCode city/airport [IATA code](http://www.iata.org/publications/Pages/code-search.aspx) to which the traveler is going, e.g. PAR for Paris (required)
     * @param departureDate the date on which the traveler will depart from the origin to go to the destination. Dates are specified in the [ISO 8601](https://en.wikipedia.org/wiki/ISO_8601) YYYY-MM-DD format, e.g. 2017-12-25 (required)
     * @param returnDate the date on which the traveler will depart from the destination to return to the origin. Dates are specified in the [ISO 8601](https://en.wikipedia.org/wiki/ISO_8601) YYYY-MM-DD format, e.g. 2018-02-28 (required)
     * @param searchDate the date on which the traveler is performing the search. If this parameter is not specified, current date will be used. Dates are specified in the [ISO 8601](https://en.wikipedia.org/wiki/ISO_8601) YYYY-MM-DD format, e.g. 2018-02-28 (optional)
     */
    @GET("v1/travel/predictions/trip-purpose")
    suspend fun getTripPurposePrediction(
        @retrofit2.http.Query("originLocationCode") originLocationCode: String,
        @retrofit2.http.Query("destinationLocationCode") destinationLocationCode: String,
        @retrofit2.http.Query("departureDate") departureDate: String,
        @retrofit2.http.Query("returnDate") returnDate: String,
        @retrofit2.http.Query("searchDate") searchDate: String?
    ): ApiResponse<Prediction>
}
