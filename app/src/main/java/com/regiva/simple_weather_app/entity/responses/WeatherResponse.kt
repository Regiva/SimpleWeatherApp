package com.regiva.simple_weather_app.entity.responses

import com.google.gson.annotations.SerializedName

//todo look downward

data class WeatherResponse(
    val cod: Int,
    val message: Float,
    val cnt: Int,
    val list: List<OneDayWeatherEntity>
)

data class OneDayWeatherEntity(
    @SerializedName("dt") val date: Long,
    @SerializedName("main") val mainInfo: MainInfoEntity,
    @SerializedName("weather") val weather: WeatherEntity,
    @SerializedName("clouds") val clouds: CloudsEntity,
    @SerializedName("wind") val wind: WindEntity,
    @SerializedName("sys") val sys: SysEntity,
    @SerializedName("dt_txt") val dateText: String
)

data class MainInfoEntity(
    val temp: Float,
    val temp_min: Float,
    val temp_max: Float,
    val pressure: Float,
    val sea_level: Float,
    val grnd_level: Float,
    val humidity: Float,
    val temp_kf: Float
)

data class WeatherEntity(
    val id: Int,
    val main: String,
    val description: String,
    val icon: String
)

data class CloudsEntity(
    val all: Int
)

data class WindEntity(
    val speed: Float,
    val deg: Float
)

data class SysEntity(
    val pod: String
)

/*{
      "dt": 1487246400,
      "main": {
        "temp": 286.67,
        "temp_min": 281.556,
        "temp_max": 286.67,
        "pressure": 972.73,
        "sea_level": 1046.46,
        "grnd_level": 972.73,
        "humidity": 75,
        "temp_kf": 5.11
      },
      "weather": [
        {
          "id": 800,
          "main": "Clear",
          "description": "clear sky",
          "icon": "01d"
        }
      ],
      "clouds": {
        "all": 0
      },
      "wind": {
        "speed": 1.81,
        "deg": 247.501
      },
      "sys": {
        "pod": "d"
      },
      "dt_txt": "2017-02-16 12:00:00"
    }*/