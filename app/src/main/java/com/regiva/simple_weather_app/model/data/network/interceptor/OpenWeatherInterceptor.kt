package com.regiva.simple_weather_app.model.data.network.interceptor

import com.regiva.simple_weather_app.Constants
import okhttp3.Interceptor
import okhttp3.Response

class OpenWeatherInterceptor : Interceptor {

    override fun intercept(chain: Interceptor.Chain): Response {
        val url = chain.request().url().newBuilder().addQueryParameter("appid", Constants.Api.API_KEY)
            .addQueryParameter("mode", "json")
            .addQueryParameter("units", "metric").build()
        return chain.proceed(chain.request().newBuilder().addHeader("Accept", "application/json").url(url).build())
    }

}