package com.example.androidchallenge.network.api

import com.example.androidchallenge.network.models.WeatherData
import retrofit2.Call
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.http.GET
import retrofit2.http.Query

private const val key = "59ac41458256ef1fc2ebfddda1ded2da"

interface WeatherApi {

    @GET("data/2.5/onecall?appid=$key&exclude=alerts,minutely&units=imperial")
    suspend fun getWeatherForecast(
        @Query("lat") lat: String,
        @Query("lon") lon: String
    ): Response<WeatherData>

    companion object {
        private const val BASE_URL = "https://api.openweathermap.org/"
        val weatherService = Retrofit.Builder()
            .baseUrl(BASE_URL)
            .addConverterFactory(GsonConverterFactory.create())
            .build()
            .create(WeatherApi::class.java)
    }
}