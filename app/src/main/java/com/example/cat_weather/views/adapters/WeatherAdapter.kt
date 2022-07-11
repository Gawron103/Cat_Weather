package com.example.cat_weather.views.adapters

import android.annotation.SuppressLint
import android.view.LayoutInflater
import android.view.MotionEvent
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.cat_weather.R
import com.example.cat_weather.databinding.ItemWeatherBinding
import com.example.cat_weather.models.CityData
import com.example.cat_weather.utils.AirPollutionMapper
import com.example.cat_weather.utils.TemperatureConverter

class WeatherAdapter: RecyclerView.Adapter<WeatherAdapter.WeatherViewHolder>() {

    private var cities: MutableList<CityData> = mutableListOf()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): WeatherViewHolder {
        val itemBinding = ItemWeatherBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return WeatherViewHolder(itemBinding.root, itemBinding)
    }

    override fun onBindViewHolder(holder: WeatherViewHolder, position: Int) {
        holder.bind(cities[position])
    }

    override fun getItemCount(): Int {
        return cities.size
    }

    fun setCities(newCities: List<CityData>) {
        cities.clear()
        cities.addAll(newCities)
        notifyDataSetChanged()
    }

    class WeatherViewHolder(private val itemView: View, private val itemBinding: ItemWeatherBinding): RecyclerView.ViewHolder(itemBinding.root) {

        @SuppressLint("ClickableViewAccessibility")
        fun bind(item: CityData) {
            itemBinding.rvForecast.apply {
                layoutManager = LinearLayoutManager(
                    itemView.context, RecyclerView.VERTICAL, false
                )
                adapter = ForecastAdapter(item.forecastModel)
                setHasFixedSize(true)
            }
            itemBinding.rvForecast.setOnTouchListener { view, event ->
                if (event.actionMasked == MotionEvent.ACTION_UP) {
                    itemBinding.root.parent.requestDisallowInterceptTouchEvent(false)
                }
                else {
                    itemBinding.root.parent.requestDisallowInterceptTouchEvent(true)
                }
                view.onTouchEvent(event)
            }

            itemBinding.tvCityName.text = item.weatherModel.name
            itemBinding.tvCurrentTemp.text = itemView.resources.getString(R.string.celsius_sign,TemperatureConverter.convertKelvinToCelsius(item.weatherModel.main.temp))
            itemBinding.tvCurrentWeatherDesc.text = item.weatherModel.weather[0].description

            itemBinding.tvAirQualityValue.text = AirPollutionMapper.getAirQualityText(
                item.airPollutionModel.list[0].main.aqi
            )
        }
    }

}