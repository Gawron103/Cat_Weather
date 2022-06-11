package com.example.cat_weather.views.home

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.viewModels
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.cat_weather.databinding.FragmentWeatherViewBinding
import com.example.cat_weather.views.adapters.WeatherAdapter
import dagger.hilt.android.AndroidEntryPoint

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
        viewModel.getWeatherAndImageForCities(cities)
        setupRecyclerView()
        observeWeatherData()
    }

    private fun observeWeatherData() {
        viewModel.citiesLoadingLiveData.observe(viewLifecycleOwner, { isLoading ->
            if (isLoading) {
                showLoading(true)
            } else {
                showLoading(false)
            }
        })

        viewModel.citiesDataLiveData.observe(viewLifecycleOwner, { cities ->
            if (cities != null) {
                weatherAdapter.setCities(cities)
            }
        })

        viewModel.cityFetchErrorLiveData.observe(viewLifecycleOwner, {
            /* To Do */
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