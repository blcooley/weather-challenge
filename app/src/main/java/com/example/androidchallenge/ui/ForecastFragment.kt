package com.example.androidchallenge.ui

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.activity.OnBackPressedCallback
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import com.example.androidchallenge.databinding.FragmentForecastBinding
import com.example.androidchallenge.network.api.WeatherApi
import com.example.androidchallenge.network.models.WeatherData
import com.example.androidchallenge.ui.viewmodel.WeatherViewModel
import com.example.androidchallenge.ui.viewmodel.WeatherViewModelFactory

class ForecastFragment : Fragment() {
    private var _binding: FragmentForecastBinding? = null
    private val binding get() = _binding!!

    private val weatherViewModel: WeatherViewModel by activityViewModels {
        WeatherViewModelFactory(WeatherApi.weatherService)
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentForecastBinding.inflate(inflater, container, false)

        weatherViewModel.uiState.observe(viewLifecycleOwner) { uiState ->
            when (uiState.loadingStatus) {
                LoadingStatus.LOADING -> { binding.progressBar.visibility = View.VISIBLE }
                LoadingStatus.SUCCESS -> {
                    binding.progressBar.visibility = View.GONE
                    bindWeatherData(uiState.weatherData)
                }
                LoadingStatus.INIT -> { binding.progressBar.visibility = View.GONE }
                LoadingStatus.ERROR -> {
                    binding.progressBar.visibility = View.GONE
                    Toast.makeText(requireContext(), uiState.errorString, Toast.LENGTH_LONG).show()
                }
            }

        }
        return binding.root
    }

    private fun bindWeatherData(data: WeatherData) {
        binding.forecastRecyclerView.adapter = ForecastAdapter(data.daily)
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}