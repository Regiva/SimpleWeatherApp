package com.regiva.simple_weather_app

import android.app.Application
import com.regiva.simple_weather_app.di.DI
import com.regiva.simple_weather_app.di.module.AppModule
import toothpick.Toothpick
import toothpick.configuration.Configuration

class App : Application() {

    override fun onCreate() {
        super.onCreate()
        toothpick()
        appScope()
//        Fresco.initialize(this)
    }

    private fun toothpick() {
        if (BuildConfig.DEBUG) {
            Toothpick.setConfiguration(Configuration.forDevelopment().preventMultipleRootScopes())
        } else {
            Toothpick.setConfiguration(Configuration.forProduction())
        }
    }

    private fun appScope() {
        Toothpick.openScope(DI.APP_SCOPE)
            .installModules(AppModule(this))
    }

}
