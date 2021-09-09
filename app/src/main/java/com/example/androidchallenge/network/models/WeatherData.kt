package com.example.androidchallenge.network.models

import com.google.gson.annotations.SerializedName

data class WeatherData(
    val lat: Double,
    val lon: Double,
    val timezone: String,
    val current: CurrentWeather
) {
    companion object {
        val EMPTY = WeatherData(0.0, 0.0, "", CurrentWeather.EMPTY)
    }
}

data class CurrentWeather(
    val dt: Long,
    val sunrise: Long,
    val sunset: Long,
    val temp: Double,
    @SerializedName("feels_like")
    val feelsLike: Double,
    val pressure: Int,
    val humidity: Int,
    val weather: List<Weather>,
    @SerializedName("wind_speed")
    val windSpeed: Double,
    @SerializedName("wind_deg")
    val windDirection: Int,
) {
    companion object {
        val EMPTY = CurrentWeather(0, 0, 0, 0.0, 0.0, 0, 0, listOf(), 0.0, 0)
    }
}

data class Weather(
    val id: Int,
    val main: String,
    val description: String,
    val icon: String
)


/*
{
    "lat": 40.12,
    "lon": -96.66,
    "timezone": "America/Chicago",
    "timezone_offset": -18000,
    "current": {
    "dt": 1595243443,
    "sunrise": 1595243663,
    "sunset": 1595296278,
    "temp": 293.28,
    "feels_like": 293.82,
    "pressure": 1016,
    "humidity": 100,
    "dew_point": 293.28,
    "uvi": 10.64,
    "clouds": 90,
    "visibility": 10000,
    "wind_speed": 4.6,
    "wind_deg": 310,
    "weather": [
    {
        "id": 501,
        "main": "Rain",
        "description": "moderate rain",
        "icon": "10n"
    },
    {
        "id": 201,
        "main": "Thunderstorm",
        "description": "thunderstorm with rain",
        "icon": "11n"
    }
    ],
    "rain": {
        "1h": 2.93
    }
},
    }*/