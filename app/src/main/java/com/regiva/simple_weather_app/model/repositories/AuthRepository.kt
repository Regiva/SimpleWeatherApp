package com.regiva.simple_weather_app.model.repositories

import com.regiva.simple_weather_app.model.data.storage.Prefs
import javax.inject.Inject

class AuthRepository @Inject constructor(
    private val prefs: Prefs
) {

    fun storeToken(token: String) {
        if (token != prefs.token) prefs.token = token
    }

}