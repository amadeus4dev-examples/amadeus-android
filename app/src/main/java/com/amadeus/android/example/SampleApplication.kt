package com.amadeus.android.example

import android.app.Application
import com.amadeus.android.Amadeus

class SampleApplication: Application() {

    override fun onCreate() {
        super.onCreate()
        amadeus = Amadeus.Builder(this)
            .setClientId(getString(R.string.amadeus_client_id))
            .setClientSecret(getString(R.string.amadeus_client_secret))
            .setCustomAppIdAndVersion("com.amadeus.android.demo", "1.0.0")
            .setLogLevel(Amadeus.Builder.LogLevel.BODY)
            .setHostName(Amadeus.Builder.Hosts.TEST)
            .build()
    }

    companion object {
        lateinit var amadeus: Amadeus
    }
}