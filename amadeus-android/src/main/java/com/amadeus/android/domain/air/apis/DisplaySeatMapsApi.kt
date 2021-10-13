package com.amadeus.android.domain.air.apis

import com.amadeus.android.ApiResponse
import com.amadeus.android.domain.resources.SeatMap
import retrofit2.http.GET
import retrofit2.http.POST

@JvmSuppressWildcards
interface DisplaySeatMapsApi {
    /**
     * Returns all the seat maps of a given flightOffer.
     * This operation allows to retrieve the seat map of each flight included in a flight offer.  Consumer can request the seat map for up to 6 flight Offer in the same query.  The reply is a list of seat map linked to its impacted segment and flight offer.
     * The endpoint is owned by defaultname service owner
     * @param body (required)
     */
    @POST("v1/shopping/seatmaps")
    suspend fun getSeatmapFromFlightOffer(
        @retrofit2.http.Body body: Map<String, Any>
    ): ApiResponse<List<SeatMap>>
    /**
     * Returns all the seat maps of a given order.
     * This operation allows to retrieve the seat map of each flight present in an order.  The reply is a list of seat map linked to its impacted segment. 
     * The endpoint is owned by defaultname service owner
     * @param flightOrderId identifier of the order (required)
     */
    @GET("v1/shopping/seatmaps")
    suspend fun getSeatmapFromOrder(
        @retrofit2.http.Query("flight-orderId") flightOrderId: String
    ): ApiResponse<List<SeatMap>>
}
