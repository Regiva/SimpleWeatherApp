package com.regiva.simple_weather_app.model.repositories

import com.regiva.simple_weather_app.model.data.network.ApiService
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers
import javax.inject.Inject

class WeatherRepository @Inject constructor(
    private val apiService: ApiService
) {

    fun getWeatherForFiveDays() =
        apiService.getWeatherForFiveDays()
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())

}