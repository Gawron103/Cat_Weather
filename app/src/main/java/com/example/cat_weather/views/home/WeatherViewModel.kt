package com.example.cat_weather.views.home

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.cat_weather.apis.WeatherApiResponse
import com.example.cat_weather.models.CityWeatherModel
import com.example.cat_weather.repositories.WeatherRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.async
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class WeatherViewModel @Inject constructor(
    private val repository: WeatherRepository
): ViewModel() {

    private val _citiesWeatherData = MutableLiveData<WeatherApiResponse>()
    val citiesWeatherData: LiveData<WeatherApiResponse> get() = _citiesWeatherData

    fun requestFullWeatherForCities(cities: List<String>) {
        viewModelScope.launch(Dispatchers.IO) {
            _citiesWeatherData.postValue(WeatherApiResponse.Loading(true))

            val data = mutableListOf<CityWeatherModel>()

            cities.forEach { city ->
                val weatherData = async { repository.getWeatherForCity(city) }
                val unsplashData = async { repository.getPhotoOfCity(city) }

                if (weatherData.await().isSuccessful && unsplashData.await().isSuccessful) {
                    /* Here perform additional check of statuses */
                    val model = CityWeatherModel(weatherData.await().body()!!, unsplashData.await().body()?.results?.get(0)?.urls?.small!!)
                        data.add(model)
                } else {
                    _citiesWeatherData.postValue(WeatherApiResponse.Error("Dummy"))
                }
            }

            _citiesWeatherData.postValue(WeatherApiResponse.Success(data))
        }
    }

}