package com.example.androidchallenge.ui

import android.content.Context
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import androidx.navigation.fragment.findNavController
import com.example.androidchallenge.R
import com.example.androidchallenge.databinding.FragmentCurrentWeatherBinding
import com.example.androidchallenge.extension.loadIcon
import com.example.androidchallenge.network.api.WeatherApi
import com.example.androidchallenge.network.models.WeatherData
import com.example.androidchallenge.ui.viewmodel.WeatherViewModel
import com.example.androidchallenge.ui.viewmodel.WeatherViewModelFactory

class CurrentWeatherFragment : Fragment() {

    private var _binding: FragmentCurrentWeatherBinding? = null
    private val binding get() = _binding!!

    private val weatherViewModel: WeatherViewModel by activityViewModels {
        WeatherViewModelFactory(WeatherApi.weatherService)
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentCurrentWeatherBinding.inflate(inflater, container, false)
        binding.forecastButton.setOnClickListener {
            val directions =
                CurrentWeatherFragmentDirections.actionCurrentWeatherFragmentToForecastFragment()
            findNavController().navigate(directions)
        }

        binding.swipeRefreshLayout.setOnRefreshListener { weatherViewModel.refreshWeatherData() }
        weatherViewModel.uiState.observe(viewLifecycleOwner) { uiState ->
            when (uiState.loadingStatus) {
                LoadingStatus.LOADING -> {
                    binding.progressBar.visibility = View.VISIBLE
                }
                LoadingStatus.SUCCESS -> {
                    binding.progressBar.visibility = View.GONE
                    bindWeatherData(uiState.weatherData)
                }
                LoadingStatus.INIT -> {
                    binding.progressBar.visibility = View.GONE
                }
                LoadingStatus.ERROR -> {
                    binding.progressBar.visibility = View.GONE
                    Toast.makeText(requireContext(), uiState.errorString, Toast.LENGTH_LONG).show()
                }
            }
        }

        return binding.root
    }

    private fun bindWeatherData(data: WeatherData) {
        with(binding) {
            locationText.text = getString(R.string.new_york_ny)
            feelsLikeText.text = getString(R.string.feels_like_format, data.current.feelsLike)
            atmosphericPressureTextValue.text =
                getString(R.string.pressure_format, data.current.pressure)
            atmosphericPressureText.visibility = View.VISIBLE
            humidityTextValue.text = getString(R.string.humidity_format, data.current.humidity)
            humidityText.visibility = View.VISIBLE
            latLongText.text = getString(R.string.location_format, data.lat, data.lon)
            temperatureText.text = getString(R.string.temperature_format, data.current.temp)
            windSpeedAndDirectionTextValue.text =
                getString(R.string.wind_format, data.current.windSpeed, data.current.windDirection)
            windSpeedAndDirectionText.visibility = View.VISIBLE
            weatherImage.loadIcon(data.current.weather[0].icon)
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}