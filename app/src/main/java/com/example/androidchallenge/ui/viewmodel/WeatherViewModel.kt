package com.example.androidchallenge.ui.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.androidchallenge.network.api.WeatherApi
import com.example.androidchallenge.network.models.WeatherData
import com.example.androidchallenge.ui.LoadingStatus
import com.example.androidchallenge.ui.UiState
import kotlinx.coroutines.launch

class WeatherViewModel(private val weatherService: WeatherApi) : ViewModel() {
    private val lat = "40.725302"
    private val long = "-73.997776"

    private val mutableUiState = MutableLiveData(UiState())
    val uiState: LiveData<UiState> = mutableUiState

    init {
        refreshWeatherData()
    }

    fun refreshWeatherData(latitude: String = lat, longitude: String = long) {
        val previousUiState = uiState.value
        mutableUiState.value = UiState(previousUiState?.weatherData ?: WeatherData.EMPTY, LoadingStatus.LOADING, previousUiState?.errorString ?: "")
        viewModelScope.launch {
            val response = weatherService.getWeatherForecast(latitude, longitude)
            if (response.isSuccessful) {
                mutableUiState.value = UiState(response.body() ?: WeatherData.EMPTY, LoadingStatus.SUCCESS, "")
            } else {
                mutableUiState.value = UiState(WeatherData.EMPTY, LoadingStatus.ERROR, response.message())
            }
        }
    }
}