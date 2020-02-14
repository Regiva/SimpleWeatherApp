package com.regiva.simple_weather_app.model.data.error

import okhttp3.ResponseBody
import java.lang.RuntimeException

class ServerError(
    val code: Int,
    val responseBody: ResponseBody? = null
) : RuntimeException()