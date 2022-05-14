package com.example.cat_weather.api

import com.example.cat_weather.models.OpenWeatherCurrentWeatherModel

sealed class OpenWeatherResponse(
    val statusOpen: OpenWeatherStatus,
    val data: OpenWeatherCurrentWeatherModel?,
    val message: String?,
    val loading: Boolean?
) {

    data class Success(val _data: OpenWeatherCurrentWeatherModel?): OpenWeatherResponse(
        statusOpen = OpenWeatherStatus.Success,
        data = _data,
        message = null,
        loading = null
    )

    data class Error(val _message: String): OpenWeatherResponse(
        statusOpen = OpenWeatherStatus.Error,
        data = null,
        message = _message,
        loading = null
    )

    data class Loading(val _loading: Boolean): OpenWeatherResponse(
        statusOpen = OpenWeatherStatus.Loading,
        data = null,
        message = null,
        loading = _loading
    )

}