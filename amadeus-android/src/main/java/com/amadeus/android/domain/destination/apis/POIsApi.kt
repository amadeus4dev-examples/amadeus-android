package com.amadeus.android.domain.destination.apis

import com.amadeus.android.ApiResponse
import com.amadeus.android.domain.resources.Location
import com.amadeus.android.tools.CSV
import retrofit2.http.GET

@JvmSuppressWildcards
interface POIsApi {
    /**
     * Returns points of interest for a given location and radius.
     * Returns Points of Interest for a given location and radius.  ### Request example: <pre><code>   GET https://test.api.amadeus.com/v1/reference-data/locations/pois?latitude=41.397158&longitude=2.160873&radius=2</code></pre>  ### Response example: <pre><code> {   \"meta\": {         \"count\": 5,         \"links\": {             \"self\": \"http://test.api.amadeus.com/v1/reference-data/locations/pois?latitude=41.397158&longitude=2.160873&radius=2\",             \"next\": \"http://test.api.amadeus.com/v1/reference-data/locations/pois?latitude=41.397158&longitude=2.160873&radius=2&page[offset]=5\",             \"last\": \"http://test.api.amadeus.com/v1/reference-data/locations/pois?latitude=41.397158&longitude=2.160873&radius=2&page[offset]=10\"         }     },   \"data\": [     {       \"type\": \"location\",       \"id\": \"3216547684\",       \"self\": {         \"href\": \"http://test.api.amadeus.com/v1/reference-data/locations/pois/3216547684\",         \"methods\": [ \"GET\" ]       },       \"subType\": \"POINT_OF_INTEREST\",       \"name\": \"Sagrada Familia\",       \"rank\": \"1\",       \"geoCode\": {         \"latitude\": 41.3987,         \"longitude\": 2.4123         },         \"category\": \"SIGHTS\",         \"tags\": [           \"amazing\",           \"building\",           \"church\",           \"tour\"         ]     },     {       \"type\": \"location\",       \"id\": \"3216547655\",       \"self\": {         \"href\": \"http://test.api.amadeus.com/v1/reference-data/locations/pois/3216547655\",         \"methods\": [ \"GET\" ]       },       \"subType\": \"POINT_OF_INTEREST\",       \"name\": \"Casa Mila\",       \"rank\": \"2\",       \"geoCode\": {         \"latitude\": 41.395214,         \"longitude\": 2.16167         },         \"category\": \"SIGHTS\",         \"tags\": [           \"museum\",           \"sights\",           \"landmark\"         ]     },     {       \"type\": \"location\",       \"id\": \"3216547675\",       \"self\": {         \"href\": \"http://test.api.amadeus.com/v1/reference-data/locations/pois/3216547675\",         \"methods\": [ \"GET\" ]       },       \"subType\": \"POINT_OF_INTEREST\",       \"name\": \"La Pepita\",       \"rank\": \"3\",       \"geoCode\": {         \"latitude\": 41.39799,         \"longitude\": 2.161113         },         \"category\": \"RESTAURANT\",         \"tags\": [           \"restaurant\",           \"tapas\"         ]     },     {       \"type\": \"location\",       \"id\": \"3216547612\",       \"self\": {         \"href\": \"http://test.api.amadeus.com/v1/reference-data/locations/pois/3216547612\",         \"methods\": [ \"GET\" ]       },       \"subType\": \"POINT_OF_INTEREST\",       \"name\": \"Botafumeiro\",       \"rank\": \"4\",       \"geoCode\": {         \"latitude\": 41.40042,         \"longitude\": 2.154638         },         \"category\": \"RESTAURANT\",         \"tags\": [           \"restaurant\",           \"seafood\"         ]     },     {       \"type\": \"location\",       \"id\": \"3216547656\",       \"self\": {         \"href\": \"http://test.api.amadeus.com/v1/reference-data/locations/pois/3216547656\",         \"methods\": [ \"GET\" ]       },       \"subType\": \"POINT_OF_INTEREST\",       \"name\": \"Sonora Sport Tavern\",       \"rank\": \"5\",       \"geoCode\": {         \"latitude\": 41.397903,         \"longitude\": 2.189959         },         \"category\": \"RESTAURANT\",         \"tags\": [           \"restaurant\",           \"bar\"         ]     }   ] } </code></pre>
     * The endpoint is owned by defaultname service owner
     * @param latitude Latitude (decimal coordinates) (required)
     * @param longitude Longitude (decimal coordinates) (required)
     * @param radius radius of the search in Kilometer. Can be from 0 to 20, default value is 1 Km. (optional, default to 1)
     * @param pageLimit maximum items in one page (optional, default to 10)
     * @param pageOffset start index of the requested page (optional, default to 0)
     * @param categories category of the location.   Multiple value can be selected using a comma i.e. SIGHTS, SHOPPING  (optional)
     */
    @GET("v1/reference-data/locations/pois")
    suspend fun getPointsOfInterest(
        @retrofit2.http.Query("latitude") latitude: Double,
        @retrofit2.http.Query("longitude") longitude: Double,
        @retrofit2.http.Query("radius") radius: Int?,
        @retrofit2.http.Query("page[limit]") pageLimit: Int?,
        @retrofit2.http.Query("page[offset]") pageOffset: Int?,
        @retrofit2.http.Query("categories") @CSV categories: List<String>?
    ): ApiResponse<List<Location>>
    /**
     * Returns points of interest for a given area
     * Returns Points of Interest for a given area.  ### Request example: <pre><code>   GET https://test.api.amadeus.com/v1/reference-data/locations/pois/by-square?north=41.397158&west=2.160873&south=41.394582&east=2.177181</code></pre>  ### Response example: <pre><code>   {   \"meta\": {         \"count\": 5,         \"links\": {             \"self\": \"http://test.api.amadeus.com/v1/reference-data/locations/pois/by-square?north=41.397158&west=2.160873&south=41.394582&east=2.177181\",             \"next\": \"http://test.api.amadeus.com/v1/reference-data/locations/pois/by-square?north=41.397158&west=2.160873&south=41.394582&east=2.177181&page[offset]=5\",             \"last\": \"http://test.api.amadeus.com/v1/reference-data/locations/pois/by-square?north=41.397158&west=2.160873&south=41.394582&east=2.177181&page[offset]=10\"         }     },   \"data\": [     {       \"type\": \"location\",       \"id\": \"3216547684\",       \"self\": {         \"href\": \"http://test.api.amadeus.com/v1/reference-data/locations/pois/3216547684\",         \"methods\": [ \"GET\" ]       },       \"subType\": \"POINT_OF_INTEREST\",       \"name\": \"Sagrada Familia\",       \"rank\": \"1\",       \"geoCode\": {         \"latitude\": 41.3987,         \"longitude\": 2.4123         },         \"category\": \"SIGHTS\",         \"tags\": [           \"amazing\",           \"building\",           \"church\",           \"tour\"         ]     },     {       \"type\": \"location\",       \"id\": \"3216547655\",       \"self\": {         \"href\": \"http://test.api.amadeus.com/v1/reference-data/locations/pois/3216547655\",         \"methods\": [ \"GET\" ]       },       \"subType\": \"POINT_OF_INTEREST\",       \"name\": \"Casa Mila\",       \"rank\": \"2\",       \"geoCode\": {         \"latitude\": 41.395214,         \"longitude\": 2.16167         },         \"category\": \"SIGHTS\",         \"tags\": [           \"museum\",           \"sights\",           \"landmark\"         ]     },     {       \"type\": \"location\",       \"id\": \"3216547675\",       \"self\": {         \"href\": \"http://test.api.amadeus.com/v1/reference-data/locations/pois/3216547675\",         \"methods\": [ \"GET\" ]       },       \"subType\": \"POINT_OF_INTEREST\",       \"name\": \"La Pepita\",       \"rank\": \"3\",       \"geoCode\": {         \"latitude\": 41.39799,         \"longitude\": 2.161113         },         \"category\": \"RESTAURANT\",         \"tags\": [           \"restaurant\",           \"tapas\"         ]     },     {       \"type\": \"location\",       \"id\": \"3216547612\",       \"self\": {         \"href\": \"http://test.api.amadeus.com/v1/reference-data/locations/pois/3216547612\",         \"methods\": [ \"GET\" ]       },       \"subType\": \"POINT_OF_INTEREST\",       \"name\": \"Botafumeiro\",       \"rank\": \"4\",       \"geoCode\": {         \"latitude\": 41.40042,         \"longitude\": 2.154638         },         \"category\": \"RESTAURANT\",         \"tags\": [           \"restaurant\",           \"seafood\"         ]     },     {       \"type\": \"location\",       \"id\": \"3216547656\",       \"self\": {         \"href\": \"http://test.api.amadeus.com/v1/reference-data/locations/pois/3216547656\",         \"methods\": [ \"GET\" ]       },       \"subType\": \"POINT_OF_INTEREST\",       \"name\": \"Sonora Sport Tavern\",       \"rank\": \"5\",       \"geoCode\": {         \"latitude\": 41.397903,         \"longitude\": 2.189959         },         \"category\": \"RESTAURANT\",         \"tags\": [           \"restaurant\",           \"bar\"         ]     }   ] } </code></pre>
     * The endpoint is owned by defaultname service owner
     * @param north Latitude north of bounding box (decimal coordinates) (required)
     * @param west Longitude west of bounding box (decimal coordinates) (required)
     * @param south Latitude south of bounding box (decimal coordinates) (required)
     * @param east Longitude east of bounding box (decimal coordinates) (required)
     * @param pageLimit maximum items in one page (optional, default to 10)
     * @param pageOffset start index of the requested page (optional, default to 0)
     * @param categories category of the location.   Multiple value can be selected using a comma i.e. SIGHTS, SHOPPING  (optional)
     */
    @GET("v1/reference-data/locations/pois/by-square")
    suspend fun getPointsOfInterestBySquare(
        @retrofit2.http.Query("north") north: Double,
        @retrofit2.http.Query("west") west: Double,
        @retrofit2.http.Query("south") south: Double,
        @retrofit2.http.Query("east") east: Double,
        @retrofit2.http.Query("page[limit]") pageLimit: Int?,
        @retrofit2.http.Query("page[offset]") pageOffset: Int?,
        @retrofit2.http.Query("categories") @CSV categories: List<String>?
    ): ApiResponse<List<Location>>

    @GET("v1/reference-data/locations/pois/{poisId}")
    suspend fun getPointOfInterest(
        @retrofit2.http.Path("poisId") poisId: String
    ): ApiResponse<Location>
}
