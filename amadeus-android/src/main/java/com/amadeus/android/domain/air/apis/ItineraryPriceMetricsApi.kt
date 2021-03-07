package com.amadeus.android.domain.air.apis

import com.amadeus.android.ApiResponse
import com.amadeus.android.domain.resources.ItineraryPriceMetric
import retrofit2.http.GET

@JvmSuppressWildcards
interface ItineraryPriceMetricsApi {
    /**
     * Returns itinerary price metrics
     * This operation allows retrieving price metrics for a given flight in a quartile distribution.  ### Request example: What's the price metrics for the given flight? <pre><code> GET https://test.api.amadeus.com/v1/analytics/itinerary-price-metrics?originIataCode=MAD&destinationIataCode=CDG&departureDate=2021-03-21&currencyCode=EUR&oneWay=false </code></pre> ### Response example: <pre><code> { "meta": { "count": 1, "links": { "self": "https://test.api.amadeus.com/v1/analytics/itinerary-price-metrics?originIataCode=MAD&destinationIataCode=CDG&departureDate=2021-03-21&currencyCode=EUR&oneWay=false" } }, "data": [ { "type": "itinerary-price-metric", "origin": { "iataCode": "MAD" }, "destination": { "iataCode": "CDG" }, "departureDate": "2021-03-21", "transportType": "FLIGHT", "currencyCode": "EUR", "oneWay": false, "priceMetrics": [ { "amount": "43.27", "quartileRanking": "MINIMUM" }, { "amount": "228.65", "quartileRanking": "FIRST" }, { "amount": "231.03", "quartileRanking": "MEDIUM" }, { "amount": "234.48", "quartileRanking": "THIRD" }, { "amount": "442.62", "quartileRanking": "MAXIMUM" } ] } ] }</code></pre>
     * The endpoint is owned by defaultname service owner
     * @param originIataCode city/airport [IATA code](http://www.iata.org/publications/Pages/code-search.aspx) from which the traveler is departing, e.g. PAR for Paris (required)
     * @param destinationIataCode city/airport [IATA code](http://www.iata.org/publications/Pages/code-search.aspx) to which the traveler is departing, e.g. PAR for Paris (required)
     * @param departureDate the date on which the traveler will depart from the origin to go to the destination. Dates are specified in the [ISO 8601](https://en.wikipedia.org/wiki/ISO_8601) YYYY-MM-DD format, e.g. 2019-12-25 (required)
     * @param currencyCode currencyCode the preferred currency for the flight offers. Currency is specified in the [ISO 4217](https://en.wikipedia.org/wiki/ISO_4217) format, e.g. EUR for Euro (optional)
     * @param oneWay oneWay if this parameter is set to true, only one-way flights are considered. If this parameter is not set or set to false, only round-trip flights are considered (optional, default to false)
     */
    @GET("v1/analytics/itinerary-price-metrics")
    suspend fun getItineraryPriceMetrics(
        @retrofit2.http.Query("originIataCode") originIataCode: String,
        @retrofit2.http.Query("destinationIataCode") destinationIataCode: String,
        @retrofit2.http.Query("departureDate") departureDate: String,
        @retrofit2.http.Query("currencyCode") currencyCode: String?,
        @retrofit2.http.Query("oneWay") oneWay: Boolean?
    ): ApiResponse<List<ItineraryPriceMetric>>
}