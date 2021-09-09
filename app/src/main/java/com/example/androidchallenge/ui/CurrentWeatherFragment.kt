package com.example.androidchallenge.ui

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
import com.example.androidchallenge.network.models.WeatherData

class CurrentWeatherFragment : Fragment() {

    private var _binding: FragmentCurrentWeatherBinding? = null
    private val binding get() = _binding!!

    private val weatherViewModel: WeatherViewModel by activityViewModels()

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentCurrentWeatherBinding.inflate(inflater, container, false)
        binding.forecastButton.setOnClickListener {
            val directions = CurrentWeatherFragmentDirections.actionCurrentWeatherFragmentToForecastFragment()
            findNavController().navigate(directions)
        }

        weatherViewModel.uiState.observe(viewLifecycleOwner) { uiState ->
            when (uiState.loadingStatus) {
                LoadingStatus.LOADING -> {}
                LoadingStatus.SUCCESS -> {
                    bindWeatherData(uiState.weatherData)
                }
                LoadingStatus.INIT -> {}
                LoadingStatus.ERROR -> {
                    Toast.makeText(requireContext(), uiState.errorString, Toast.LENGTH_LONG).show()
                }
            }
        }

        return binding.root
    }

    private fun bindWeatherData(data: WeatherData) {
        binding.feelsLikeText.text = getString(R.string.feels_like_format, data.current.feelsLike)
        binding.atmosphericPressureText.text = getString(R.string.pressure_format, data.current.pressure)
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}