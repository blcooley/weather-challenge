package com.example.androidchallenge.ui.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.example.androidchallenge.network.api.WeatherApi

class WeatherViewModelFactory(
    private val weatherApi: WeatherApi
) : ViewModelProvider.Factory {

    override fun <T : ViewModel?> create(
        modelClass: Class<T>,
    ): T {
        if (modelClass.isAssignableFrom(WeatherViewModel::class.java)) {
            @Suppress("UNCHECKED_CAST")
            return WeatherViewModel(weatherApi) as T
        }
        throw IllegalArgumentException("Unknown ViewModel class")
    }
}
