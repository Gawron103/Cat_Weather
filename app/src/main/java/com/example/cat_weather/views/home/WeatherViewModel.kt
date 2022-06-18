package com.example.cat_weather.views.home

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.cat_weather.models.CityData
import com.example.cat_weather.repositories.WeatherRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.async
import kotlinx.coroutines.launch
import timber.log.Timber
import javax.inject.Inject

@HiltViewModel
class WeatherViewModel @Inject constructor(
    private val repository: WeatherRepository
): ViewModel() {

    private val _citiesLoadingLiveData = MutableLiveData<Boolean>()
    val citiesLoadingLiveData: LiveData<Boolean> get() = _citiesLoadingLiveData

    private val _citiesDataLiveData = MutableLiveData<List<CityData>>()
    val citiesDataLiveData: LiveData<List<CityData>> get() = _citiesDataLiveData

    private val _cityFetchErrorLiveData = MutableLiveData<String>()
    val cityFetchErrorLiveData: LiveData<String> get() = _cityFetchErrorLiveData

    fun getWeatherAndImageForCities(cities: List<String>) {
        viewModelScope.launch(Dispatchers.IO) {
            _citiesLoadingLiveData.postValue(true)

            val citiesData = mutableListOf<CityData>()

            for (city in cities) {
                val weatherResponse = async { repository.getWeatherByName(city) }
                val cityImageResponse = async { repository.getCityImage(city) }

                val weatherData = weatherResponse.await()
                val cityImage = cityImageResponse.await()

                if (weatherData.data != null) {
                    if (cityImage.data != null) {
                        val forecastResponse = repository.getForecastByCords(weatherData.data.coord.lat, weatherData.data.coord.lon)

                        if (forecastResponse.data != null) {
                            val airPollutionResponse = repository.getAirPollutionForCords(weatherData.data.coord.lat, weatherData.data.coord.lon)

                            if (airPollutionResponse.data != null) {
                                Timber.d("City Added")
                                citiesData.add(CityData(weatherData.data, forecastResponse.data, airPollutionResponse.data, cityImage.data))
                            } else {
                                Timber.d("Cannot get city air pollution")
                                Timber.d("Error: ${airPollutionResponse.message}")
                            }
                        } else {
                            Timber.d("Cannot get city forecast")
                        }
                    } else {
                        Timber.d("Cannot get city image")
                    }
                } else {
                    Timber.d("Cannot get city weather")
                }
            }

            _citiesDataLiveData.postValue(citiesData)

            _citiesLoadingLiveData.postValue(false)
        }
    }

}