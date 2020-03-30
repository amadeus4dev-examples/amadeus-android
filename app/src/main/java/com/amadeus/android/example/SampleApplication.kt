package com.amadeus.android.example

import android.app.Application
import com.amadeus.android.Amadeus
import com.amadeus.android.BuildConfig

class SampleApplication: Application() {

    override fun onCreate() {
        super.onCreate()
        amadeus = Amadeus.Builder(this)
            .setClientId(BuildConfig.AMADEUS_CLIENT_ID)
            .setClientSecret(BuildConfig.AMADEUS_CLIENT_SECRET)
            .setCustomAppIdAndVersion("com.amadeus.android.demo", "1.0.0")
            .setLogLevel(Amadeus.Builder.LogLevel.BODY)
            .setHostName(Amadeus.Builder.Hosts.TEST)
            .build()
    }

    companion object {
        lateinit var amadeus: Amadeus
    }
}