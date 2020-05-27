package com.amadeus.android.service

import com.amadeus.android.Amadeus
import com.amadeus.android.ApiResult
import com.amadeus.android.ApiResult.Success
import com.amadeus.android.BuildConfig
import com.amadeus.android.domain.resources.AirTraffic
import com.amadeus.android.domain.resources.Traveler
import com.amadeus.android.domain.resources.Traveler.*
import com.amadeus.android.succeeded
import com.amadeus.android.tools.TypesAdapterFactory
import com.amadeus.android.tools.XNullableAdapterFactory
import com.squareup.moshi.Moshi
import com.squareup.moshi.Types
import kotlinx.coroutines.delay
import kotlinx.coroutines.runBlocking
import org.junit.BeforeClass
import org.junit.FixMethodOrder
import org.junit.Test
import org.junit.runners.MethodSorters


@Suppress("BlockingMethodInNonBlockingContext")
@FixMethodOrder(MethodSorters.NAME_ASCENDING)
class AmadeusTest {

    companion object {
        lateinit var amadeus: Amadeus
        lateinit var moshi: Moshi

        @BeforeClass
        @JvmStatic
        fun before() {
            amadeus = Amadeus.Builder()
                .setHostName(Amadeus.Builder.Hosts.TEST)
                .setClientId(BuildConfig.AMADEUS_CLIENT_ID)
                .setClientSecret(BuildConfig.AMADEUS_CLIENT_SECRET)
                .setLogLevel(Amadeus.Builder.LogLevel.BODY)
                .build()

            moshi = Moshi.Builder()
                .add(XNullableAdapterFactory())
                .add(TypesAdapterFactory())
                .build()
        }
    }

    @Test
    fun `Refresh Access token`() = runBlocking {
        val response = amadeus.refreshToken()
        println(response)
    }

    @Test
    fun `Check-in Links`() = runBlocking {
        assert(amadeus.referenceData.urls.checkinLinks.get("LH").succeeded)
    }

    @Test
    fun `Locations Airports`() = runBlocking {
        assert(
            amadeus.referenceData.locations.airports.get(40.416775, -3.703790).succeeded
        )
    }

    @Test
    fun `Locations search`() = runBlocking {
        assert(
            amadeus.referenceData.locations.get(
                listOf("AIRPORT", "CITY"),
                "r",
                pageLimit = 5
            ).succeeded
        )
    }

    @Test
    fun `Location by ID`() = runBlocking {
        assert(amadeus.referenceData.location("ALHR").get().succeeded)
    }

    @Test
    fun `Airline by name`() = runBlocking {
        assert(amadeus.referenceData.airlines.get("BA").succeeded)
    }

    @Test
    fun `FlightDestinations by origin`() = runBlocking {
        assert(amadeus.shopping.flightDestinations.get("BOS").succeeded)
    }

    @Test
    fun `FlightDates cheapest`() = runBlocking {
        assert(amadeus.shopping.flightDates.get("BOS", "PHL").succeeded)
    }

    @Test
    fun `FlightOffers for origin and destination`() = runBlocking {
        assert(
            amadeus.shopping.flightOffersSearch.get(
                "MAD",
                "MUC",
                "2020-10-22",
                1
            ).succeeded
        )
    }

    @Test
    fun `FlightOrders by id`() = runBlocking {
        assert(
            amadeus.booking.flightOrder("eJzTd9cPCzZ1CgsAAAtqAmw=").get().succeeded
        )
    }

    @Test
    fun `AI Prediction for BOS`() = runBlocking {
        assert(
            amadeus.airport.predictions.onTime.get(
                "BOS",
                "2020-05-22"
            ).succeeded
        )
    }

    @Test
    fun `Busiest period in 2017 for MAD`() = runBlocking {
        assert(
            amadeus.travel.analytics.airTraffic.busiestPeriod.get(
                "MAD",
                "2017",
                "ARRIVING"
            ).succeeded
        )
    }

    @Test
    fun `Busiest period in 2017 for MAD by url`() = runBlocking {
        val result = amadeus.travel.analytics.airTraffic.busiestPeriod.get(
            "MAD",
            "2017",
            "ARRIVING"
        )
        delay(1000)
        val stringResult = amadeus.get(
            "https://test.api.amadeus.com/v1/travel/analytics/air-traffic/busiest-period?cityCode=MAD&period=2017&direction=ARRIVING"
        ).orEmpty()
        assert(result.succeeded && stringResult.isNotBlank())
        val type = Types.newParameterizedType(
            List::class.java,
            AirTraffic::class.java
        )
        val resultType = Types.newParameterizedTypeWithOwner(
            ApiResult::class.java,
            Success::class.java,
            type
        )
        val adapter = moshi.adapter<Success<List<AirTraffic>>>(resultType)
        val resultToCompare = adapter.fromJson(stringResult)
        assert(result == resultToCompare)
    }

    @Test
    fun `Most Boobked period in 2017 for MAD`() = runBlocking {
        assert(
            amadeus.travel.analytics.airTraffic.booked.get(
                "MAD",
                "2017-05"
            ).succeeded
        )
    }

    @Test
    fun `Most traveled period in 2017 for MAD`() = runBlocking {
        assert(
            amadeus.travel.analytics.airTraffic.traveled.get(
                "MAD",
                "2017-05"
            ).succeeded
        )
    }

    @Test
    fun `Hotel sentiment by id`() = runBlocking {
        assert(
            amadeus.ereputation.hotelSentiments.get(
                listOf("TELONMFS", "ADNYCCTB", "XXXYYY01")
            ).succeeded
        )
    }

    @Test
    fun `Hotel search - Hotel offers`() = runBlocking {
        assert(
            amadeus.shopping.hotelOffers.get(
                cityCode = "PAR",
                adults = 1,
                radius = 5,
                radiusUnit = "KM",
                paymentPolicy = "NONE",
                includeClosed = false,
                bestRateOnly = true,
                view = "FULL",
                sort = "PRICE"
            ).succeeded
        )
    }

    @Test
    fun `Hotel Rooms Search, price and book - Hotel offers by id`() = runBlocking {
        val offers = amadeus.shopping.hotelOffersByHotel.get(
            hotelId = "BGMILBGB",
            checkInDate = "2020-10-01",
            checkOutDate = "2020-10-02",
            adults = 2,
            roomQuantity = 1,
            paymentPolicy = "NONE",
            view = "FULL_ALL_IMAGES"
        )
        when (offers) {
            is Success -> {
                   val offer = amadeus.shopping.hotelOffer(offers.data.offers?.get(0)?.id ?: "").get()
                    assert(offer.succeeded)
                if (offer is Success) {
                    val body = "{" +
                            "  \"data\": {" +
                            "    \"offerId\": " + (offer.data.offers?.get(0)?.id ?: null) + "," +
                            "    \"guests\": [" +
                            "      {" +
                            "        \"id\": 1," +
                            "        \"name\": {" +
                            "          \"title\": \"MR\"," +
                            "          \"firstName\": \"BOB\"," +
                            "          \"lastName\": \"SMITH\"" +
                            "        }," +
                            "        \"contact\": {" +
                            "          \"phone\": \"+33679278416\"," +
                            "          \"email\": \"bob.smith@email.com\"" +
                            "        }" +
                            "      }" +
                            "    ]," +
                            "    \"payments\": [" +
                            "      {" +
                            "        \"id\": 1," +
                            "        \"method\": \"creditCard\"," +
                            "        \"card\": {" +
                            "          \"vendorCode\": \"VI\"," +
                            "          \"cardNumber\": \"4151289722471370\"," +
                            "          \"expiryDate\": \"2021-08\"" +
                            "        }" +
                            "      }" +
                            "    ]," +
                            "    \"rooms\": [" +
                            "      {" +
                            "        \"guestIds\": [" +
                            "          1" +
                            "        ]," +
                            "        \"paymentId\": 1," +
                            "        \"specialRequest\": \"I will arrive at midnight\"" +
                            "      }\n" +
                            "    ]" +
                            "  }" +
                            "}"

                    amadeus.booking.hotelBooking.post(body);
                }
            }
            else -> assert(false)
        }
    }

    @Test
    fun `Hotel search - Hotel offers by hotels`() = runBlocking {
        assert(
            amadeus.shopping.hotelOffersByHotel.get(
                hotelId = "BGMILBGB",
                checkInDate = "2020-10-01",
                checkOutDate = "2020-10-02",
                adults = 2,
                roomQuantity = 1,
                paymentPolicy = "NONE",
                view = "FULL_ALL_IMAGES"
            ).succeeded
        )
    }

    @Test
    fun `Prediction trip purpose`() = runBlocking {
        assert(
            amadeus.travel.predictions.tripPurpose.get(
                originLocationCode = "NYC",
                destinationLocationCode = "MAD",
                departureDate = "2020-08-01",
                returnDate = "2020-08-12",
                searchDate = "2020-06-11"
            ).succeeded
        )
    }

    @Test
    fun `Get POI by id`() = runBlocking {
        assert(
            amadeus.referenceData.locations.pointsOfInterest("9CB40CB5D0").get().succeeded
        )
    }

    @Test
    fun `Get POI by location`() = runBlocking {
        assert(
            amadeus.referenceData.locations.pointsOfInterest.get(
                latitude = 41.397158,
                longitude = 2.160873,
                radius = 2
            ).succeeded
        )
    }

    @Test
    fun `Get POI by square`() = runBlocking {
        assert(
            amadeus.referenceData.locations.pointsOfInterest.bySquare.get(
                north = 41.397158,
                west = 2.160873,
                south = 41.394582,
                east = 2.177181
            ).succeeded
        )
    }

    @Test
    fun `Generate Photo`() = runBlocking {
        assert(amadeus.media.files.generatedPhotos.get("MOUNTAIN").succeeded)
    }

    @Test
    fun `Seat map for offer id`() = runBlocking {
        assert(amadeus.shopping.seatMaps.get("eJzTd9f3NjIJdzUGAAp%2fAiY=").succeeded)
    }

    @Test
    fun `Quote Flight Offer with object`() = runBlocking {
        val result = amadeus.shopping.flightOffersSearch.get(
            originLocationCode = "MAD",
            destinationLocationCode = "MUC",
            departureDate = "2020-10-22",
            adults = 1,
            max = 2
        )
        assert(result.succeeded)
        if (result is Success) {
            val flightOfferSearches = result.data
            assert(amadeus.shopping.flightOffersSearch.pricing.post(flightOfferSearches).succeeded)
        }
    }

    @Test
    fun `Booking FlightCreateOrder with object, retrieve and delete`() = runBlocking {
        // Create fake traveler
        val traveler = Traveler(
            id = "1",
            dateOfBirth = "2000-04-14",
            name = Name("JORGE", "GONZALES"),
            contact = Contact(
                listOf(
                    Phone(
                        countryCallingCode = "33",
                        number = "675426222",
                        deviceType = "MOBILE"
                    )
                )
            ),
            documents = listOf(
                Document(
                    documentType = "PASSPORT",
                    number = "480080076",
                    expiryDate = "2022-10-11",
                    issuanceCountry = "ES",
                    nationality = "ES",
                    holder = true
                )
            )
        )

        val flightOffers = amadeus.shopping.flightOffersSearch.get(
            originLocationCode = "NCE",
            destinationLocationCode = "PAR",
            departureDate = "2020-11-01",
            returnDate = "2020-11-08",
            adults = 1,
            max = 3
        )
        assert(flightOffers.succeeded)
        if (flightOffers is Success) {
            val pricing =
                amadeus.shopping.flightOffersSearch.pricing.post(flightOffers.data.first())
            assert(pricing.succeeded)
            if (pricing is Success) {
                val order = amadeus.booking.flightOrders.post(
                    flightPrice = pricing.data,
                    travelers = listOf(traveler)
                )
                assert(order.succeeded)
                if (order is Success) {
                    order.data.id?.let {
                        assert(amadeus.booking.flightOrder(it).get().succeeded)
                        assert(amadeus.booking.flightOrder(it).delete() is Success)
                    } ?: assert(false) { "Order doesn't have id" }
                }
            }
        }
    }

    @Test
    fun `Next - Get POI by location`() = runBlocking {
        val result = amadeus.referenceData.locations.pointsOfInterest.get(
            latitude = 41.397158,
            longitude = 2.160873,
            radius = 2
        )
        assert(result.succeeded)
        if (result is Success) {
            val next = amadeus.next(result)
            println("Next result: $next")
            assert(next?.succeeded ?: false)
        }
    }

    @Test
    fun `Seat map post`() = runBlocking {
        val flightOffers =
            amadeus.get("https://test.api.amadeus.com/v2/shopping/flight-offers?originLocationCode=MAD&destinationLocationCode=MUC&departureDate=2020-10-22&adults=1&max=1")
        assert(flightOffers?.let { amadeus.shopping.seatMaps.post(it).succeeded } ?: false)
    }

    @Test
    fun `Flight Delay Prediction`() = runBlocking {
        assert(
            amadeus.travel.predictions.flightDelay.get(
                originLocationCode = "NCE",
                destinationLocationCode = "IST",
                departureDate = "2020-08-01",
                departureTime = "18:20:00",
                arrivalDate = "2020-08-01",
                arrivalTime = "22:15:00",
                aircraftCode = "321",
                carrierCode = "TK",
                flightNumber = "1816",
                duration = "PT31H10M"
            ).succeeded
        )
    }
}