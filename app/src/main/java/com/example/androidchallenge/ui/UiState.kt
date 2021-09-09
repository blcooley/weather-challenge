package com.example.androidchallenge.ui

import com.example.androidchallenge.network.models.WeatherData

data class UiState(
    val weatherData: WeatherData = WeatherData.EMPTY,
    val loadingStatus: LoadingStatus = LoadingStatus.INIT,
    val errorString: String = ""
)

enum class LoadingStatus {
    INIT, LOADING, SUCCESS, ERROR
}