package com.app.assessment

import android.app.Application
import android.util.Log

class MainApplication : Application() {

    override fun onCreate() {
        super.onCreate()

        // Initialize global libraries or dependencies here
        Log.d("MainApplication", "Application Started")
        // Example: Initialize Timber for logging
        // Timber.plant(Timber.DebugTree())
    }
}