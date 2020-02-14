package com.regiva.simple_weather_app.model.interactors

import com.regiva.simple_weather_app.model.repositories.AuthRepository
import com.regiva.simple_weather_app.model.repositories.TokenRepository
import javax.inject.Inject

class AuthInteractor @Inject constructor(
    private val authRepository: AuthRepository,
    private val tokenRepository: TokenRepository
) {

    fun isLoggedIn() = tokenRepository.isLoggedIn()

    fun storeToken(token: String) = authRepository.storeToken(token)

}