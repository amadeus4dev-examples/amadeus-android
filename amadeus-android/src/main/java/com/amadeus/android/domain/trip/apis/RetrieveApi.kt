package com.amadeus.android.domain.trip.apis

import com.amadeus.android.ApiResponse
import com.amadeus.android.domain.resources.Location
import retrofit2.http.GET

@JvmSuppressWildcards
interface RetrieveApi {
    /**
     * Retieve one point of interest by its Id.
     * Returns a single Point of Interest from a given id.  ### Request example:  <pre><code>   GET https://test.api.amadeus.com/v1/reference-data/locations/pois/9CB40CB5D0</code></pre>  ### Request example: <pre><code> {   \"data\": {     \"type\": \"location\",     \"subType\": \"POINT_OF_INTEREST\",     \"id\": \"9CB40CB5D0\",     \"self\": {         \"href\": \"https://test.api.amadeus.com/v1/reference-data/locations/pois/9CB40CB5D0\",         \"methods\": [             \"GET\"         ]     },     \"geoCode\": {         \"latitude\": 41.39165,         \"longitude\": 2.164772     },     \"name\": \"Casa Batlló\",     \"category\": \"SIGHTS\",     \"rank\": 1,     \"tags\": [         \"sightseeing\",         \"sights\",         \"museum\",         \"landmark\",         \"tourguide\",         \"restaurant\",         \"attraction\",         \"activities\",         \"commercialplace\",         \"shopping\",         \"souvenir\"     ]   } } </code></pre> 
     * The endpoint is owned by defaultname service owner
     * @param poisId identifier of the pois (required)
     */
    @GET("reference-data/locations/pois/{poisId}")
    suspend fun getPointOfInterest(
        @retrofit2.http.Path("poisId") poisId: String
    ): ApiResponse<Location>
}
