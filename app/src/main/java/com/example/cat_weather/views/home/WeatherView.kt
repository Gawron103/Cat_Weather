package com.example.cat_weather.views.home

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.viewModels
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.cat_weather.apis.ApiStatus
import com.example.cat_weather.databinding.FragmentWeatherViewBinding
import com.example.cat_weather.views.adapters.WeatherAdapter
import dagger.hilt.android.AndroidEntryPoint
import timber.log.Timber

@AndroidEntryPoint
class WeatherView : Fragment() {

    private var _binding: FragmentWeatherViewBinding? = null
    private val binding: FragmentWeatherViewBinding get() = _binding!!

    private val viewModel by viewModels<WeatherViewModel>()

    private lateinit var weatherAdapter: WeatherAdapter

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentWeatherViewBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        val cities = getSavedCitiesFromFirebase()
        viewModel.requestFullWeatherForCities(cities)
        setupRecyclerView()
        observeFullDataResponse()
    }

    private fun observeFullDataResponse() {
        viewModel.citiesWeatherData.observe(viewLifecycleOwner, { response ->
            when (response.status) {
                ApiStatus.Success -> {
                    Timber.d(response.data.toString())
                    response.data?.let {
                        weatherAdapter.setCities(it)
                    }
                    showLoading(false)
                }
                ApiStatus.Error -> {
                    showLoading(false)
                    Timber.e(response.message)
                }
                ApiStatus.Loading -> {
                    showLoading(true)
                    Timber.d("View should show loading")
                }
            }
        })
    }

    private fun setupRecyclerView() {
        weatherAdapter = WeatherAdapter()

        binding.rvWeatherRecycler.apply {
            layoutManager = LinearLayoutManager(
                requireContext(), RecyclerView.HORIZONTAL, false
            )
            adapter = weatherAdapter
        }
    }

    private fun showLoading(isLoading: Boolean) {
        binding.pbWeatherLoading.visibility = if (isLoading) View.VISIBLE else View.GONE
    }

    private fun getSavedCitiesFromFirebase(): List<String> {
        return listOf("Szczecin", "Berlin", "Amsterdam")
    }



}