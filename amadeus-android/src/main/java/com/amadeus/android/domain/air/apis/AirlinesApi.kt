package com.amadeus.android.domain.air.apis

import com.amadeus.android.ApiResponse
import com.amadeus.android.domain.resources.Airline
import retrofit2.http.GET

@JvmSuppressWildcards
interface AirlinesApi {
    /**
     * Return airlines information.
     * This operation returns names and code for all airlines.   If you already know what you are looking for, you can search for specific airlines using IATA code ([IATA table codes](http://www.iata.org/publications/Pages/code-search.aspx)) or ICAO code ([ICAO airlines table codes](https://en.wikipedia.org/wiki/List_of_airline_codes)).   ### Request example: What’s the airline name for the IATA code BA? <pre><code> GET https://test.api.amadeus.com/v1/reference-data/airlines?airlineCodes=BA </code></pre>   ### Response example: <pre><code>     {     \"meta\": {         \"count\": 1,         \"links\": {             \"self\": \"https://test.api.amadeus.com/v1/reference-data/airlines?airlineCodes=BA\"         }     },     \"data\": [         {             \"type\": \"airline\",             \"iataCode\": \"BA\",             \"icaoCode\": \"BAW\",             \"businessName\": \"BRITISH AIRWAYS\",             \"commonName\": \"BRITISH A/W\"         }     ] } </code></pre>
     * The endpoint is owned by defaultname service owner
     * @param airlineCodes Code of the airline following IATA standard([IATA table codes](http://www.iata.org/publications/Pages/code-search.aspx)) or ICAO standard ([ICAO airlines table codes](https://en.wikipedia.org/wiki/List_of_airline_codes))  Several airlines can be selected at once by sending a list separated by a coma (i.e. AF, SWA)  (optional)
     */
    @GET("reference-data/airlines")
    suspend fun getairlines(
        @retrofit2.http.Query("airlineCodes") airlineCodes: String?
    ): ApiResponse<List<Airline>>
}
