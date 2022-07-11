package com.example.cat_weather.views.adapters

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.cat_weather.R
import com.example.cat_weather.databinding.ItemWeatherHourlyForecastBinding
import com.example.cat_weather.models.forecastmodel.WeatherForecast
import com.example.cat_weather.utils.TemperatureConverter
import com.example.cat_weather.utils.TimeConverter
import timber.log.Timber

class HourlyForecastAdapter(private val forecast: List<WeatherForecast>): RecyclerView.Adapter<HourlyForecastAdapter.HourlyForecastViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): HourlyForecastViewHolder {
        val itemBinding = ItemWeatherHourlyForecastBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return HourlyForecastViewHolder(itemBinding)
    }

    override fun onBindViewHolder(holder: HourlyForecastViewHolder, position: Int) {
        holder.bind(forecast[position])
    }

    override fun getItemCount(): Int {
        return forecast.size
    }

    class HourlyForecastViewHolder(private val itemBinding: ItemWeatherHourlyForecastBinding): RecyclerView.ViewHolder(itemBinding.root) {

        fun bind(item: WeatherForecast) {
            Timber.d("DT: ${item.dt}")
            itemBinding.tvHourlyForecastTime.text = TimeConverter.convertTime(item.dt.toInt())
            itemBinding.tvHourlyForecastTemp.text = itemView.resources.getString(R.string.celsius_sign, TemperatureConverter.convertKelvinToCelsius(item.main.temp))

            Glide
                .with(itemBinding.ivHourlyForecastWeatherIcon)
                .load("https://openweathermap.org/img/wn/${item.weather[0].icon}@2x.png")
                .error(R.drawable.ic_error)
                .centerCrop()
                .into(itemBinding.ivHourlyForecastWeatherIcon)
        }

    }

}