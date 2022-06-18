package com.example.cat_weather.repositories

import com.example.cat_weather.BuildConfig
import com.example.cat_weather.apis.OpenWeatherApi
import com.example.cat_weather.apis.ResponseState
import com.example.cat_weather.apis.UnsplashApi
import com.example.cat_weather.models.airpollutionmodel.AirPollutionModel
import com.example.cat_weather.models.forecastmodel.ForecastModel
import com.example.cat_weather.models.weatherbycordsmodel.WeatherByCordsModel
import com.example.cat_weather.models.weatherbynamemodel.WeatherByNameModel
import javax.inject.Inject

class WeatherRepositoryImpl @Inject constructor(
    private val openWeatherApi: OpenWeatherApi,
    private val unsplashApi: UnsplashApi
) : WeatherRepository {

    override suspend fun getWeatherByCords(lat: Double, lon: Double): ResponseState<WeatherByCordsModel?> {
        return try {
            val response = openWeatherApi.getWeatherForCords(lat, lon, "minutely|daily|alert", BuildConfig.OPEN_WEATHER_KEY)

            if (response.isSuccessful) {
                val data = response.body()
                ResponseState.Success(data)
            } else {
                ResponseState.Success(null)
            }
        } catch (e: Exception) {
            if (null != e.message) {
                ResponseState.Error(e.message!!)
            } else {
                ResponseState.Error("TEST")
            }
        }
    }

    override suspend fun getWeatherByName(name: String): ResponseState<WeatherByNameModel?> {
        return try {
            val response = openWeatherApi.getWeatherForCity(name, BuildConfig.OPEN_WEATHER_KEY)

            if (response.isSuccessful) {
                val data = response.body()
                ResponseState.Success(data)
            } else {
                ResponseState.Success(null)
            }
        } catch (e: Exception) {
            if (null != e.message) {
                ResponseState.Error(e.message!!)
            } else {
                ResponseState.Error("TEST")
            }
        }
    }

    override suspend fun getForecastByCords(lat: Double, lon: Double): ResponseState<ForecastModel?> {
        return try {
        val response = openWeatherApi.getForecastForCords(lat, lon, BuildConfig.OPEN_WEATHER_KEY)

        if (response.isSuccessful) {
            val data = response.body()
            ResponseState.Success(data)
        } else {
            ResponseState.Success(null)
        }
    } catch (e: Exception) {
            if (null != e.message) {
                ResponseState.Error(e.message!!)
            } else {
                ResponseState.Error("TEST")
            }
        }
    }

    override suspend fun getCityImage(name: String): ResponseState<String?> {
        return try {
            val response = unsplashApi.getPhotoForCity(name, "portrait")

            if (response.isSuccessful) {
                val data = response.body()?.results?.get(0)?.urls?.small!!
                ResponseState.Success(data)
            } else {
                ResponseState.Success(null)
            }
        } catch (e: Exception) {
            if (null != e.message) {
                ResponseState.Error(e.message!!)
            } else {
                ResponseState.Error("Unknown error")
            }
        }
    }

    override suspend fun getAirPollutionForCords(lat: Double, lon: Double): ResponseState<AirPollutionModel?> {
        return try {
            val response = openWeatherApi.getAirPollutionForCords(lat, lon, BuildConfig.OPEN_WEATHER_KEY)

            if (response.isSuccessful) {
                val data = response.body()
                ResponseState.Success(data)
            } else {
                ResponseState.Success(null)
            }
        } catch (e: Exception) {
            if (null != e.message) {
                ResponseState.Error(e.message!!)
            } else {
                ResponseState.Error("TEST")
            }
        }
    }

}