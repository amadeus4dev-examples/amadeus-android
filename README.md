# Amadeus Android (Kotlin) SDK

[![Build Status](https://travis-ci.org/amadeus4dev/amadeus-android.svg?branch=master)][travis]
[![Contact Support](https://github.com/amadeus4dev/amadeus-android/raw/master/.github/images/support.svg?sanitize=true)][support]
[![Discord](https://img.shields.io/discord/696822960023011329?label=&logo=discord&logoColor=ffffff&color=7389D8&labelColor=6A7EC2)](https://discord.gg/cVrFBqx)

Amadeus has a rich set of APIs for the travel industry. For more details, check out the [Amadeus for Developers Portal](https://developers.amadeus.com).

## Installation

This library requires Java version 1.8 and Kotlin version 1.3.70 minimum.

You can install the SDK via Maven or Gradle.

#### Maven
```xml
<dependency>
  <groupId>com.amadeus</groupId>
  <artifactId>amadeus-android</artifactId>
  <version>0.0.5</version>
</dependency>
```
#### Gradle
```kotlin
implementation 'com.amadeus:amadeus-android:0.0.5'
```

## Getting Started

To make your first API call, you will need to [register](https://developers.amadeus.com/register) for an Amadeus Developer Account and [set up your first application](https://developers.amadeus.com/my-apps).

```kotlin
// Being in an Activity/Fragment/ViewModel or any file you want
val amadeus = Amadeus.Builder(context)
    .setClientId("REPLACE_BY_YOUR_API_KEY")
    .setClientSecret("REPLACE_BY_YOUR_API_SECRET")
    .build()

// Your kotlin Coroutine scope
scope.launch {
    when (val result = SampleApplication.amadeus.shopping.hotelOffers.get(
        cityCode = destination,
        checkInDate = checkInDate,
        checkOutDate = checkOutDate
    )) {
        is Result.Success -> {
            if (result.succeeded) {
                Log.d("{$result.data}")
            } else {
                //call return without data
            }
        }
        is Result.Error -> // Handle your error
    }
}
```

As you can see, we don't throw `Exceptions` (except for some specific cases) in the API, but we provide a `Result.Error` object with all the information you need to know. Coroutines and exceptions are not good friends, so with this abstraction, you can handle every use case you want in a safe way.

## Initialization

The client can be initialized using a dedicated builder:

```kotlin
val amadeus = Amadeus.Builder(context)
    .setClientId("REPLACE_BY_YOUR_API_KEY")
    .setClientSecret("REPLACE_BY_YOUR_API_SECRET")
    .build()
```

Alternatively, it can be initialized without any parameters if the string resources `R.string.amadeus_client_id` and `R.string.amadeus_client_secret` are present.

```kotlin
Amadeus amadeus = Amadeus.Builder(context).build();
```

*__Warning__: Do not commit your credentials on GitHub.*

We recommend to add the credentials by including them in your app gradle file using one of these methods:

```kotlin
// Credentials from system env. variables, placed in App BuildConfig
dev {
    // Your build config...

    //Amadeus credentials
    if (System.getenv('amadeus.api.key') != null) {
        buildConfigField "String", "AMADEUS_CLIENT_ID", System.getenv('amadeus.api.key')
    } else {
        buildConfigField "String", "AMADEUS_CLIENT_ID", ""
    }
    if (System.getenv('amadeus.api.secret') != null) { // Same as above
        buildConfigField "String", "AMADEUS_CLIENT_SECRET", System.getenv('amadeus.api.secret')
    } else {
        buildConfigField "String", "AMADEUS_CLIENT_SECRET", ""
    }
}

// Credentials from user-level gradle.properties files, placed in App Strings
// /!\ This method is suitable to use Builder without credentials parameters. /!\
dev {
    // Your build config...

    //Amadeus credentials
    if (project.hasProperty("amadeus.api.key")) {
      resValue "string", "amadeus_client_id", property("amadeus.api.key")
    }
    if (project.hasProperty("amadeus.api.secret")) {
        resValue "string", "amadeus_client_secret", property("amadeus.api.secret")
    }
}
```

*__Note__: These are just examples, you can mix and match these approaches.*

Your credentials can be found on the [Amadeus dashboard](https://developers.amadeus.com/my-apps).

By default, the SDK environment is set to `test` environment. To switch to a `production` (pay-as-you-go) environment, please switch the hostname as follows:

```kotlin
val amadeus = Amadeus.Builder(context)
    .setHostName(Amadeus.Builder.Hosts.PRODUCTION)
    .build();
```

## Documentation

Amadeus has a large set of APIs, and our documentation is here to get you started today. Head over to our [API documentation](https://developers.amadeus.com/self-service)  for in-depth information about every API.

## Making API calls
This library conveniently maps every API path to a similar path. You have 2 ways to call the API:

The first one is by passing the mandatory parameters (in the right order) only. For example, `GET /v2/reference-data/urls/checkin-links?airlineCode=BA` would be:

```kotlin
amadeus.referenceData.urls.checkinLinks.get("BA")
```


The second way is to call the API by passing the name of the parameter before the value:
```kotlin
amadeus.referenceData.urls.checkinLinks.get(airlineCode = "BA")
```

Similarly, to select a resource by ID, you can pass in the ID to the **singular** path.

For example,  `GET /v2/shopping/hotel-offers/XXX` would be:

```kotlin
amadeus.shopping.hotelOffer("XXX").get()
```

You can make any arbitrary API call as well directly with the `.get`, `.post` or `.delete` method. You will get a raw string version of the JSON response.

```kotlin
val stringResult = amadeus.get("https://test.api.amadeus.com/v1/travel/analytics/air-traffic/busiest-period?cityCode=MAD&period=2017&direction=ARRIVING")
```

You can then cast it into the corresponding object:

```kotlin
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
val airTraffic = adapter.fromJson(stringResult)
```

## Pagination

If an API endpoint supports pagination, the other pages are available under the
`.next`, `.previous`, `.last` and `.first` methods.

```kotlin
val result = amadeus.referenceData.locations.pointsOfInterest.get(
            latitude = 41.397158,
            longitude = 2.160873,
            radius = 2
        )

// Fetches the next page
val next = amadeus.next(result)
```

If a page is not available, the method will return `null`.

## Logging & Debugging
To enable more verbose logging, you can set the appropriate level on your logger. The easiest way would be to enable debugging via a parameter on initialization. You can chose between:
- `NONE`: No logs.
- `BASIC`: Logs request and response lines.
- `HEADERS`: Logs request and response lines and their respective headers.
- `BODY`: Logs request and response lines and their respective headers and bodies (if present).

```kotlin
val amadeus = Amadeus.Builder(context)
    .setLogLevel(Amadeus.Builder.LogLevel.BODY)
    .build()
```

## List of supported endpoints
```kotlin
// Flight Inspiration Search
val flightDestinations = amadeus.shopping.flightDestinations.get(origin = "MAD")

// Flight Cheapest Date Search
val flightDates = amadeus.shopping.flightDates.get(origin = "MAD", destination = "MUC")

// Flight Offer Search v2 GET
val flightOffersSearches = amadeus.shopping.flightOffersSearch.get(
                            originLocationCode = "SYD",
                            destinationLocationCode = "BKK",
                            departureDate = LocalDate.parse("2020-11-01"),
                            returnDate = LocalDate.parse("2020-11-08"),
                            adults = 2,
                            max = 3)

// Flight Offer Search v2 POST
// body can be a String version of your JSON or the body object
val flightOffersSearches = amadeus.shopping.flightOffersSearch.post(body)

// Flight Offers Price
val pricing = amadeus.shopping.flightOffersSearch.pricing.post(flightOffersSearches.data.first())

// Flight Create Orders
// You can find how to define a complete working example
// here: https://bit.ly/2zz9bTy
val order = amadeus.booking.flightOrders.post(
                    flightPrice = pricing.data,
                    travelers = listOf(traveler)
                )

// Flight Order Management
// Retrieve booking
// The flightOrderID comes from the Flight Create Orders (in test environment it's temporary)
val flightOffer = amadeus.booking.flightOrder(order.data.id).get()

// Flight Order Management
// Cancel booking
amadeus.booking.flightOrder(order.data.id)

// Flight Choice Prediction
// Note that the example calls 2 APIs: Flight Offers Search & Flight Choice Prediction v2
val flightOffersSearches = amadeus.shopping.flightOffersSearch.get(
                            originLocationCode = "SYD",
                            destinationLocationCode = "BKK",
                            departureDate = LocalDate.parse("2020-11-01"),
                            adults = 1,
                            max = 3)

// Using a JSonObject
TODO: Not implemented yet

// Using a String
TODO: Not implemented yet

// Flight Check-in Links
val checkinLinks = amadeus.referenceData.urls.checkinLinks.get(airlineCode = "LH")

// Airline Code LookUp
val airlines = amadeus.referenceData.airlines.get(airlineCodes = "BA")

// Airport & City Search (autocomplete)
// Find all the cities and airports starting by the keyword 'LON'
val locations = amadeus.referenceData.locations.get(
                  subType = listOf(Location.SubTypeEnum.AIRPORT.value, Location.SubTypeEnum.CITY.value),
                  keyword = "LON")

// Get a specific city or airport based on its id
val location = amadeus.referenceData.locations.pointsOfInterest("9CB40CB5D0").get()

// Airport Nearest Relevant (for London)
val locations = amadeus.referenceData.locations.airports.get(
                  latitude = 40.416775,
                  longitude = -3.703790)

// Flight Most Booked Destinations
val airTraffics = amadeus.travel.analytics.airTraffic.booked.get(
                    originCityCode = "MAD",
                    period = "2017-05")

// Flight Most Traveled Destinations
val airTraffics = amadeus.travel.analytics.airTraffic.traveled.get(
                    originCityCode = "MAD",
                    period = "2017-05")

// Flight Busiest Traveling Period
val busiestPeriods = amadeus.travel.analytics.airTraffic.busiestPeriod.get(
                      cityCode = "MAD",
                      period = "2017",
                      direction = "ARRIVING")

// Hotel Search API
// Get list of hotels by city code
val offers = amadeus.shopping.hotelOffers.get(cityCode = "PAR")
// Get list of offers for a specific hotel
val offers = amadeus.shopping.hotelOffersByHotel.get(hotelId = "BGLONBGB")
// Confirm the availability of a specific offer
val offer = amadeus.shopping.hotelOffer(offers.data.offers?.get(0)?.id ?: "").get()

// Hotel Ratings / Sentiments
val hotelSentiments = amadeus.ereputation.hotelSentiments.get(listOf("TELONMFS","ADNYCCTB","XXXYYY01"))

// Points of Interest
// What are the popular places in Barcelona (based a geo location and a radius)
val pointsOfInterest = amadeus.referenceData.locations.pointsOfInterest.get(
                        latitude = 41.397158,
                        longitude = 2.160873)

// What are the popular places in Barcelona? (based on a square)
val pointsOfInterest = amadeus.referenceData.locations.pointsOfInterest.bySquare.get(
                        north = 41.397158,
                        west = 2.160873,
                        south = 41.394582,
                        east = 2.177181)

// Returns a single Point of Interest from a given id
val pointOfInterest = amadeus.referenceData.locations.pointsOfInterest("9CB40CB5D0").get()

// What's the likelihood flights from this airport will leave on time?
val airportOnTime = amadeus.airport.predictions.onTime.get(
                      airportCode = "BOS",
                      date = LocalDate.parse("2020-12-01"))

// What's the likelihood of a given flight to be delayed?
val flightDelay = amadeus.travel.predictions.flightDelay.get(
                    originLocationCode = "NCE",
                    destinationLocationCode = "IST",
                    departureDate = "2020-08-01",
                    departureTime = "18:20:00",
                    arrivalDate = "2020-08-01",
                    arrivalTime = "22:15:00",
                    aircraftCode = "321",
                    carrierCode = "TK",
                    flightNumber = "1816",
                    duration = "PT31H10M")

// Trip Purpose Prediction
val tripPurpose = amadeus.travel.predictions.tripPurpose.get(
                    originLocationCode = "NYC",
                    destinationLocationCode = "MAD",
                    departureDate = "2020-12-01",
                    returnDate = "2020-12-12",
                    searchDate = "2020-05-20")

// AI Generated Photos
val generatedPhoto = amadeus.media.files.generatedPhotos.get("MOUNTAIN")

// What is the the seat map of a given flight?
val seatMap = amadeus.shopping.seatMaps.get(flightOfferId = "eJzTd9f3NjIJdzUGAAp%2fAiY=")

// What is the the seat map of a given flight?
// The body can be a String version of your JSON or a Object
val flightOffers = amadeus.get("https://test.api.amadeus.com/v2/shopping/flight-offers?originLocationCode=MAD&destinationLocationCode=MUC&departureDate=2020-10-22&adults=1&max=1")
val seatMap = amadeus.shopping.seatMaps.post(flightOffers)
```

## Development & Contributing

Want to contribute? Read our [Contributors Guide](.github/CONTRIBUTING.md) for
guidance on installing and running this code in a development environment.

## License

This library is released under the [MIT License](LICENSE).

## Help

Our [developer support team](https://developers.amadeus.com/support) is here
to help you. You can find us on
[StackOverflow](https://stackoverflow.com/questions/tagged/amadeus) and
[email](mailto:developers@amadeus.com).

[travis]: http://travis-ci.org/amadeus4dev/amadeus-android
[support]: http://developers.amadeus.com/support
