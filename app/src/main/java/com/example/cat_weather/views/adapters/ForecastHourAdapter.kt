package com.example.cat_weather.views.adapters

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.cat_weather.R
import com.example.cat_weather.databinding.ItemForecastHourBinding
import com.example.cat_weather.models.forecastmodel.WeatherForecast
import com.example.cat_weather.utils.TemperatureConverter

class ForecastHourAdapter(private val data: List<WeatherForecast>): RecyclerView.Adapter<ForecastHourAdapter.ForecastHourViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ForecastHourViewHolder {
        val itemBinding = ItemForecastHourBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return ForecastHourViewHolder(itemBinding)
    }

    override fun onBindViewHolder(holder: ForecastHourViewHolder, position: Int) {
        holder.bind(data[position])
    }

    override fun getItemCount(): Int {
        return data.size
    }

    class ForecastHourViewHolder(private val itemBinding: ItemForecastHourBinding): RecyclerView.ViewHolder(itemBinding.root) {

        fun bind(entity: WeatherForecast) {
            itemBinding.tvHourItemTime.text = entity.dt_txt.slice(11..15)
            itemBinding.tvHourItemTemp.text = itemView.resources.getString(R.string.celsius_sign,
                TemperatureConverter.convertKelvinToCelsius(entity.main.temp))
            Glide
                .with(itemBinding.ivHourItemIcon)
                .load("https://openweathermap.org/img/wn/${entity.weather[0].icon}@2x.png")
                .error(R.drawable.ic_error)
                .centerCrop()
                .into(itemBinding.ivHourItemIcon)
        }

    }
}