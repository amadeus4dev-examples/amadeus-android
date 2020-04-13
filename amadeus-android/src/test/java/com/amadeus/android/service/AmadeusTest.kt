package com.amadeus.android.service

import com.amadeus.android.Amadeus
import com.amadeus.android.BuildConfig
import com.amadeus.android.base.ApiResult
import com.amadeus.android.base.succeeded
import com.amadeus.android.domain.air.models.Location
import kotlinx.coroutines.runBlocking
import org.junit.BeforeClass
import org.junit.FixMethodOrder
import org.junit.Test
import org.junit.runners.MethodSorters
import org.threeten.bp.Clock
import org.threeten.bp.LocalDate

@FixMethodOrder(MethodSorters.NAME_ASCENDING)
class AmadeusTest {

    companion object {
        lateinit var amadeus: Amadeus

        @BeforeClass
        @JvmStatic
        fun before() {
            amadeus = Amadeus.Builder()
                .setHostName(Amadeus.Builder.Hosts.TEST)
                .setClientId(BuildConfig.AMADEUS_CLIENT_ID)
                .setClientSecret(BuildConfig.AMADEUS_CLIENT_SECRET)
                .setLogLevel(Amadeus.Builder.LogLevel.BODY)
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
        assert(amadeus.referenceData.urls.checkinLinks.get("LH")?.succeeded ?: false)
    }

    @Test
    fun `Locations Airports`() = runBlocking {
        assert(
            amadeus.referenceData.locations.airports.get(40.416775, -3.703790)?.succeeded ?: false
        )
    }

    @Test
    fun `Locations search`() = runBlocking {
        assert(
            amadeus.referenceData.locations.get(
                listOf(Location.SubTypeEnum.AIRPORT.value, Location.SubTypeEnum.CITY.value),
                "r",
                pageLimit = 5
            )?.succeeded ?: false
        )
    }

    @Test
    fun `Location by ID`() = runBlocking {
        assert(amadeus.referenceData.location("ALHR").get()?.succeeded ?: false)
    }

    @Test
    fun `Airline by name`() = runBlocking {
        assert(amadeus.referenceData.airlines.get("BA")?.succeeded ?: false)
    }

    @Test
    fun `FlightDestinations by origin`() = runBlocking {
        assert(amadeus.shopping.flightDestinations.get("BOS")?.succeeded ?: false)
    }

    @Test
    fun `FlightDates cheapest`() = runBlocking {
        assert(amadeus.shopping.flightDates.get("BOS", "PHL")?.succeeded ?: false)
    }

    @Test
    fun `FlightOffers for origin and destination`() = runBlocking {
        assert(
            amadeus.shopping.flightOffersSearch.get(
                "MAD",
                "MUC",
                LocalDate.now(Clock.systemUTC()).plusMonths(1),
                1
            )?.succeeded ?: false
        )
    }

    @Test
    fun `FlightOrders by id`() = runBlocking {
        assert(
            amadeus.booking.flightOrder("eJzTd9cPCzZ1CgsAAAtqAmw=").get()?.succeeded ?: false
        )
    }

    @Test
    fun `AI Prediction for BOS`() = runBlocking {
        assert(
            amadeus.airport.predictions.onTime.get(
                "BOS",
                LocalDate.now(Clock.systemUTC()).plusMonths(1)
            )?.succeeded ?: false
        )
    }

    @Test
    fun `Busiest period in 2017 for MAD`() = runBlocking {
        assert(
            amadeus.travel.analytics.airTraffic.busiestPeriod.get(
                "MAD",
                "2017",
                "ARRIVING"
            )?.succeeded ?: false
        )
    }

    @Test
    fun `Most Boobked period in 2017 for MAD`() = runBlocking {
        assert(
            amadeus.travel.analytics.airTraffic.booked.get(
                "MAD",
                "2017-05"
            )?.succeeded ?: false
        )
    }

    @Test
    fun `Most traveled period in 2017 for MAD`() = runBlocking {
        assert(
            amadeus.travel.analytics.airTraffic.traveled.get(
                "MAD",
                "2017-05"
            )?.succeeded ?: false
        )
    }

    @Test
    fun `Hotel sentiment by id`() = runBlocking {
        assert(
            amadeus.ereputation.hotelSentiments.get(
                listOf("TELONMFS","ADNYCCTB","XXXYYY01")
            )?.succeeded ?: false
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
            )?.succeeded ?: false
        )
    }

    @Test
    fun `Hotel search - Hotel offers by id`() = runBlocking {
        val offers = amadeus.shopping.hotelOffersByHotel.get(
            hotelId = "BGMILBGB",
            adults = 2,
            roomQuantity = 1,
            paymentPolicy = "NONE",
            view = "FULL_ALL_IMAGES"
        )
        when (offers) {
            is ApiResult.Success -> {
                assert(
                    amadeus.shopping.hotelOffer(offers.data.offers?.get(0)?.id ?: "").get()?.succeeded ?: false
                )
            }
            else -> assert(false)
        }
    }

    @Test
    fun `Hotel search - Hotel offers by hotels`() = runBlocking {
        assert(
            amadeus.shopping.hotelOffersByHotel.get(
                hotelId = "BGMILBGB",
                adults = 2,
                roomQuantity = 1,
                paymentPolicy = "NONE",
                view = "FULL_ALL_IMAGES"
            )?.succeeded ?: false
        )
    }

    @Test
    fun `Prediction trip purpose`() = runBlocking {
        assert(
            amadeus.travel.predictions.tripPurpose.get(
                originLocationCode = "NYC",
                destinationLocationCode = "MAD",
                departureDate = LocalDate.of(2020, 8, 1),
                returnDate = LocalDate.of(2020, 8, 12),
                searchDate = LocalDate.of(2020, 6, 11)
            )?.succeeded ?: false
        )
    }

    @Test
    fun `Get POI by id`() = runBlocking {
        assert(
            amadeus.referenceData.locations.pointsOfInterest("9CB40CB5D0").get()?.succeeded ?: false
        )
    }

    @Test
    fun `Get POI by location`() = runBlocking {
        assert(
            amadeus.referenceData.locations.pointsOfInterest.get(
                latitude = 41.397158,
                longitude = 2.160873,
                radius = 2
            )?.succeeded ?: false
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
            )?.succeeded ?: false
        )
    }

    @Test
    fun `Generate Photo`() = runBlocking {
        assert(amadeus.media.files.generatedPhotos.get("MOUNTAIN")?.succeeded ?: false)
    }

    @Test
    fun `Seat map for offer id`() = runBlocking {
        assert(amadeus.shopping.seatMaps.get("eJzTd9f3NjIJdzUGAAp%2fAiY=")?.succeeded ?: false)
    }
}