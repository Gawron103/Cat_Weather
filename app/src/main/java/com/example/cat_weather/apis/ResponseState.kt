package com.example.cat_weather.apis

sealed class ResponseState<T>(
    val data: T? = null,
    val message: String? = null
) {

    data class Success<T>(val _data: T): ResponseState<T>(data = _data)

    data class Error<T>(val _message: String): ResponseState<T>(message = _message)

}
