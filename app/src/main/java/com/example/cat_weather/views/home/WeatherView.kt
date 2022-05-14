package com.example.cat_weather.views.home

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.viewModels
import com.example.cat_weather.api.OpenWeatherStatus
import com.example.cat_weather.databinding.FragmentWeatherViewBinding
import dagger.hilt.android.AndroidEntryPoint
import timber.log.Timber

@AndroidEntryPoint
class WeatherView : Fragment() {

    private var _binding: FragmentWeatherViewBinding? = null
    private val binding: FragmentWeatherViewBinding get() = _binding!!

    private val viewModel by viewModels<WeatherViewModel>()

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentWeatherViewBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        viewModel.getWeatherForCity("Szczecin")
        observeWeatherData()
    }

    private fun observeWeatherData() {
        viewModel.weatherData.observe(viewLifecycleOwner, { response ->
            when (response.statusOpen) {
                OpenWeatherStatus.Success -> {
                    Timber.d(response.data.toString())
                }
                OpenWeatherStatus.Error -> {
                    Timber.e(response.message)
                }
                OpenWeatherStatus.Loading -> {
                    Timber.d("View should show loading")
                }
            }
        })
    }

}