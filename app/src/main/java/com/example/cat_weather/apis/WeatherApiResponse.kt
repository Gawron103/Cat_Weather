package com.example.cat_weather.apis

import com.example.cat_weather.models.CityWeatherModel

sealed class WeatherApiResponse(
    val status: ApiStatus,
    val data: List<CityWeatherModel>?,
    val message: String?,
    val loading: Boolean?
) {

    data class Success(val _data: List<CityWeatherModel>): WeatherApiResponse(
        status = ApiStatus.Success,
        data = _data,
        message = null,
        loading = null
    )

    data class Error(val _message: String): WeatherApiResponse(
        status = ApiStatus.Error,
        data = null,
        message = _message,
        loading = null
    )

    data class Loading(val _loading: Boolean): WeatherApiResponse(
        status = ApiStatus.Loading,
        data = null,
        message = null,
        loading = _loading
    )

}