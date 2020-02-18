package com.regiva.simple_weather_app.model.data.network

import com.regiva.simple_weather_app.entity.responses.WeatherResponse
import io.reactivex.Observable
import retrofit2.Response
import retrofit2.http.GET

interface ApiService {

    @GET("forecast?q=Kazan")
    fun getWeatherForFiveDays(): Observable<Response<WeatherResponse>>

}