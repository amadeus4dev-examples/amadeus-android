package com.amadeus.android.travel

import com.amadeus.android.base.BaseApi
import kotlinx.coroutines.CoroutineDispatcher
import okhttp3.OkHttpClient

class Searched internal constructor(
    baseUrl: String,
    httpClient: OkHttpClient,
    dispatcher: CoroutineDispatcher
) : BaseApi(dispatcher)
