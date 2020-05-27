package com.amadeus.android.domain.hotel.apis

import com.amadeus.android.ApiResponse
import com.amadeus.android.domain.resources.HotelBooking
import retrofit2.http.POST

@JvmSuppressWildcards
interface BookingApi {
    /**
     * BOOK ROOMS
     * This service allows the user to book the offer retrieved from Hotel Shopping API.  The minimum mandatory parameters are: - offerId - guest name and contact  The user can also specify payment details.  For multiple rooms, guests and payments identifiers can be defined for the repartition. The room object contains the list of guests identifiers, one payment identifier and one special request.  In case the offerId refers to more than one room, it is recommended that the user defines guests ditribution in each room. In case multiple guests are defined in guest details and no distribution is provided, each guest is assigned to a separate room.    If the booking is successful, the provider confirmation number is returned in the response. In case of multiple rooms, several confirmation numbers are returned: one per room.    ### Query example <pre><code>POST /v1/booking/hotel-bookings HTTP/1.1 Host: test.api.amadeus.com Content-Type: application/vnd.amadeus+json Content-Encoding: gzip  Accept-Encoding: gzip <br/>{   \"data\": {     \"offerId\": \"63A93695B58821ABB0EC2B33FE9FAB24D72BF34B1BD7D707293763D8D9378FC3\",     \"guests\": [       {         \"name\": {           \"title\": \"MR\",           \"firstName\": \"BOB\",           \"lastName\": \"SMITH\"         },         \"contact\": {           \"phone\": \"+33679278416\",           \"email\": \"bob.smith@email.com\"         }       }     ],     \"payments\": [       {         \"method\": \"creditCard\",         \"card\": {           \"vendorCode\": \"VI\",           \"cardNumber\": \"4111111111111111\",           \"expiryDate\": \"2023-01\"         }       }     ]   } } </code></pre>  ### Response example <pre><code>HTTP/1.1 201 Created Content-Type: application/vnd.amadeus+json Content-Encoding: gzip <br/>{   \"data\": [     {       \"type\": \"hotel-booking\",       \"id\": \"XD_8138319951754\",       \"providerConfirmationId\": \"8138319951754\"     }   ] } </code></pre> 
     * The endpoint is owned by defaultname service owner
     * @param requestBody &#x60;offerId&#x60;, &#x60;guests&#x60;, &#x60;payments&#x60; and optional &#x60;rooms&#x60; for the repartition (when used the &#x60;rooms&#x60; array items must match the shopping offer &#x60;roomQuantity&#x60;)  (required)
     */
    @POST("booking/hotel-bookings")
    suspend fun createBooking(
        @retrofit2.http.Body body: Map<String, Any>
    ): ApiResponse<List<HotelBooking>>
}
