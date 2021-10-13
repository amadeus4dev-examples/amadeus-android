package com.amadeus.android.domain.air.apis

import com.amadeus.android.ApiResponse
import com.amadeus.android.domain.resources.Prediction
import retrofit2.http.GET

@JvmSuppressWildcards
interface AirportOntimePredictionApi {
    /**
     * Returns a percentage of on-time flight departures from a given airport.
     * Returns a percentage of on-time flight departures from a given airport.  ### Request example: <pre><code>  GET https://test.api.amadeus.com/v1/airport/predictions/on-time?airportCode=JFK&date=2020-08-01 </code></pre>   ### Response example: <pre><code> {   \"data\": {       \"id\": \"JFK20200801\",       \"probability\": \"0.928\",       \"result\": \"0.74906671\",       \"subType\": \"on-time\",       \"type\": \"prediction\"   },   \"meta\": {       \"links\": {           \"self\": \"https://test.api.amadeus.com/v1/airport/predictions/on-time?airportCode=JFK&date=2020-08-01\"       }   } } </code></pre> 
     * The endpoint is owned by defaultname service owner
     * @param airportCode airport [IATA code](http://www.iata.org/publications/Pages/code-search.aspx), e.g. BOS for Boston (required)
     * @param date the date on which the traveler will depart from the give airport. Dates are specified in the [ISO 8601](https://en.wikipedia.org/wiki/ISO_8601) YYYY-MM-DD format, e.g. 2019-12-25 (required)
     */
    @GET("v1/airport/predictions/on-time")
    suspend fun getAirportOnTimePrediction(
        @retrofit2.http.Query("airportCode") airportCode: String,
        @retrofit2.http.Query("date") date: String
    ): ApiResponse<Prediction>
}
