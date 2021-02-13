package com.amadeus.android.booking

import com.amadeus.android.BaseApi
import com.amadeus.android.domain.air.apis.BookingApi
import com.amadeus.android.domain.resources.FlightOfferSearch
import com.amadeus.android.domain.resources.FlightOrder.*
import com.amadeus.android.domain.resources.FlightPrice
import com.amadeus.android.domain.resources.Traveler
import com.amadeus.android.tools.GeneratedCodeConverters
import com.squareup.moshi.Moshi
import kotlinx.coroutines.CoroutineDispatcher
import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.create

@Suppress("BlockingMethodInNonBlockingContext")
class FlightOrders internal constructor(
    retrofit: Retrofit,
    dispatcher: CoroutineDispatcher
) : BaseApi(dispatcher) {

    private val api: BookingApi = retrofit.create()

    suspend fun post(body: String) = safeApiCall {
        api.createFlightOrders(bodyAsMap(body))
    }

    suspend fun post(body: Map<String, Any>) = safeApiCall {
        api.createFlightOrders(body)
    }

    suspend fun post(
        flightPrice: FlightPrice,
        id: String? = null,
        queuingOfficeId: String? = null,
        ownerOfficeId: String? = null,
        associatedRecords: List<AssociatedRecord>? = null,
        travelers: List<Traveler>? = null,
        contacts: List<Contact>? = null,
        tickets: List<Document>? = null
    ) = post(
        flightPrice.flightOffers ?: emptyList(),
        id,
        queuingOfficeId,
        ownerOfficeId,
        associatedRecords,
        travelers,
        contacts,
        tickets
    )

    suspend fun post(
        flightOffer: FlightOfferSearch,
        id: String? = null,
        queuingOfficeId: String? = null,
        ownerOfficeId: String? = null,
        associatedRecords: List<AssociatedRecord>? = null,
        travelers: List<Traveler>? = null,
        contacts: List<Contact>? = null,
        tickets: List<Document>? = null
    ) = post(
        listOf(flightOffer),
        id,
        queuingOfficeId,
        ownerOfficeId,
        associatedRecords,
        travelers,
        contacts,
        tickets
    )

    suspend fun post(
        flightOffers: List<FlightOfferSearch>,
        id: String? = null,
        queuingOfficeId: String? = null,
        ownerOfficeId: String? = null,
        associatedRecords: List<AssociatedRecord>? = null,
        travelers: List<Traveler>? = null,
        contacts: List<Contact>? = null,
        tickets: List<Document>? = null
    ) = safeApiCall {
        /*
         * Missing entries for now:
         *
         * remarks: Remarks? = null
         * formOfPayments: List<FormOfPayment>? = null,
         * ticketingAgreement: TicketingAgreement? = null,
         * automatedProcess: List<AutomatedProcess>? = null,
         *
         */

        val bodyObject = mutableMapOf<String, Any>()
        bodyObject["type"] = "flight-order"
        bodyObject["flightOffers"] = flightOffers
        id?.let { bodyObject["id"] = it }
        queuingOfficeId?.let { bodyObject["queuingOfficeId"] = it }
        ownerOfficeId?.let { bodyObject["ownerOfficeId"] = it }
        associatedRecords?.let { bodyObject["associatedRecords"] = it }
        travelers?.let { bodyObject["travelers"] = it }
        contacts?.let { bodyObject["contacts"] = it }
        tickets?.let { bodyObject["tickets"] = it }

        val body = mutableMapOf<String, Any>()
        body["data"] = bodyObject
        api.createFlightOrders(body)
    }
}
