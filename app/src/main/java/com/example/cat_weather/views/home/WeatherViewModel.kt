package com.example.cat_weather.views.home

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.cat_weather.api.OpenWeatherResponse
import com.example.cat_weather.repositories.WeatherRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class WeatherViewModel @Inject constructor(
    private val repository: WeatherRepository
): ViewModel() {

    private val _weatherData = MutableLiveData<OpenWeatherResponse>()
    val weatherData: LiveData<OpenWeatherResponse> get() = _weatherData

    fun getWeatherForCity(name: String) {
        viewModelScope.launch(Dispatchers.IO) {
            _weatherData.postValue(OpenWeatherResponse.Loading(true))

            val response = repository.getWeatherForCity(name)

            if (response.isSuccessful) {
                _weatherData.postValue(OpenWeatherResponse.Success(response.body()))
            } else {
                val message = response.errorBody()?.string() ?: "No message"
                _weatherData.postValue(OpenWeatherResponse.Error(message))
            }
        }
    }

}