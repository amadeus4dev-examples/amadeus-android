package com.amadeus.android.token

/**
 * Provides an access token for request authorization.
 */
internal interface AccessTokenProvider {

    /**
     * Returns an access token. In the event that you don't have a token return null.
     */
    fun token(): String?

    /**
     * Returns the status of token
     */
    fun isTokenNullOrExpired(): Boolean

    /**
     * Refreshes the token and returns it. This call should be made synchronously.
     * In the event that the token could not be refreshed return null.
     */
    fun refreshToken(): String?
}