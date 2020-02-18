package com.regiva.simple_weather_app.model.interactors

import com.regiva.simple_weather_app.model.repositories.WeatherRepository
import javax.inject.Inject

class WeatherInteractor @Inject constructor(
    private val weatherRepository: WeatherRepository
) {

    fun getWeatherForFiveDays() =
        weatherRepository.getWeatherForFiveDays()

}