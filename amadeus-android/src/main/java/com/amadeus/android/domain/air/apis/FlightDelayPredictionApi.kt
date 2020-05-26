package com.amadeus.android.domain.air.apis

import com.amadeus.android.ApiResponse
import com.amadeus.android.domain.resources.Prediction
import retrofit2.http.GET

@JvmSuppressWildcards
interface FlightDelayPredictionApi {
    /**
     * Return the delay segment where the flight is likely to lay.
     * This machine learning API is based on a prediction model that takes the input of the user -time, carrier, airport and aircraft information- and predict the segment where the flight is likely to lay.  ## Request example: <pre><code> GET https://test.api.amadeus.com/v1/travel/predictions/flight-delay?originLocationCode=NCE&destinationLocationCode=IST&departureDate=2020-08-01&departureTime=18:20:00&arrivalDate=2020-08-01&arrivalTime=22:15:00&aircraftCode=321&carrierCode=TK&flightNumber=1816&duration=PT31H10M </code></pre>  ## Response examples: <pre><code> {     \"data\": [         {             \"id\": \"TK1816NCEIST20200801\",             \"probability\": \"0.13336977\",             \"result\": \"LESS_THAN_30_MINUTES\",             \"subType\": \"flight-delay\",             \"type\": \"prediction\"         },         {             \"id\": \"TK1816NCEIST20200801\",             \"probability\": \"0.42023364\",             \"result\": \"BETWEEN_30_AND_60_MINUTES\",             \"subType\": \"flight-delay\",             \"type\": \"prediction\"         },         {             \"id\": \"TK1816NCEIST20200801\",             \"probability\": \"0.34671372\",             \"result\": \"BETWEEN_60_AND_120_MINUTES\",             \"subType\": \"flight-delay\",             \"type\": \"prediction\"         },         {             \"id\": \"TK1816NCEIST20200801\",             \"probability\": \"0.09968289\",             \"result\": \"OVER_120_MINUTES_OR_CANCELLED\",             \"subType\": \"flight-delay\",             \"type\": \"prediction\"         }     ],     \"meta\": {         \"count\": 4,         \"links\": {             \"self\": \"https://test.api.amadeus.com/v1/travel/predictions/flight-delay?originLocationCode=NCE&destinationLocationCode=IST&departureDate=2020-08-01&departureTime=18:20:00&arrivalDate=2020-08-01&arrivalTime=22:15:00&aircraftCode=321&carrierCode=TK&flightNumber=1816&duration=PT31H10M\"         }     } } </code></pre> 
     * The endpoint is owned by defaultname service owner
     * @param originLocationCode city/airport [IATA code](http://www.iata.org/publications/Pages/code-search.aspx) to which the traveler is departing, e.g. PAR for Paris (required)
     * @param destinationLocationCode city/airport [IATA code](http://www.iata.org/publications/Pages/code-search.aspx) to which the traveler is departing, e.g. PAR for Paris (required)
     * @param departureDate the date on which the traveler will depart from the origin to go to the destination. Dates are specified in the [ISO 8601](https://en.wikipedia.org/wiki/ISO_8601) YYYY-MM-DD format, e.g. 2019-12-25 (required)
     * @param departureTime local time relative to originLocationCode on which the traveler will depart from the origin. Time respects ISO 8601 standard. e.g. 13:22:00 (required)
     * @param arrivalDate the date on which the traveler will arrive to the destination from the origin. Dates are specified in the [ISO 8601](https://en.wikipedia.org/wiki/ISO_8601) YYYY-MM-DD format, e.g. 2019-12-25 (required)
     * @param arrivalTime local time relative to destinationLocationCode on which the traveler will arrive to destination. Time respects ISO 8601 standard. e.g. 13:22:00 (required)
     * @param aircraftCode IATA aircraft code (http://www.flugzeuginfo.net/table_accodes_iata_en.php) (required)
     * @param carrierCode airline / carrier code (required)
     * @param flightNumber flight number as assigned by the carrier (required)
     * @param duration flight duration in [ISO8601](https://en.wikipedia.org/wiki/ISO_8601) PnYnMnDTnHnMnS format, e.g. PT2H10M (required)
     */
    @GET("travel/predictions/flight-delay")
    suspend fun getFlightDelayPrediction(
        @retrofit2.http.Query("originLocationCode") originLocationCode: String,
        @retrofit2.http.Query("destinationLocationCode") destinationLocationCode: String,
        @retrofit2.http.Query("departureDate") departureDate: String,
        @retrofit2.http.Query("departureTime") departureTime: String,
        @retrofit2.http.Query("arrivalDate") arrivalDate: String,
        @retrofit2.http.Query("arrivalTime") arrivalTime: String,
        @retrofit2.http.Query("aircraftCode") aircraftCode: String,
        @retrofit2.http.Query("carrierCode") carrierCode: String,
        @retrofit2.http.Query("flightNumber") flightNumber: String,
        @retrofit2.http.Query("duration") duration: String
    ): ApiResponse<List<Prediction>>
}
