package com.example.cat_weather.views.adapters

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.cat_weather.R
import com.example.cat_weather.databinding.ItemWeatherBinding
import com.example.cat_weather.models.CityWeatherModel
import com.example.cat_weather.utils.TemperatureConverter
import com.example.cat_weather.utils.TimeConverter
import kotlin.math.roundToInt

class WeatherAdapter: RecyclerView.Adapter<WeatherAdapter.WeatherViewHolder>() {

    private var cities: MutableList<CityWeatherModel> = mutableListOf()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): WeatherViewHolder {
        val itemBinding = ItemWeatherBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return WeatherViewHolder(itemBinding)
    }

    override fun onBindViewHolder(holder: WeatherViewHolder, position: Int) {
        holder.bind(cities[position])
    }

    override fun getItemCount(): Int {
        return cities.size
    }

    fun setCities(newCities: List<CityWeatherModel>) {
        cities.clear()
        cities.addAll(newCities)
        notifyDataSetChanged()
    }

    fun addCity(cityData: CityWeatherModel) {
        cities.add(cityData)
        notifyDataSetChanged()
    }



    class WeatherViewHolder(private val itemBinding: ItemWeatherBinding): RecyclerView.ViewHolder(itemBinding.root) {

        fun bind(item: CityWeatherModel) {
            itemBinding.tvCityName.text = item.weatherModel.name
            itemBinding.tvCurrentTemp.text = TemperatureConverter.convertKelvinToCelsius(item.weatherModel.main.temp).toString()
            itemBinding.tvCurrentWeatherDesc.text = item.weatherModel.weather[0].description
            itemBinding.tvSunriseValue.text = TimeConverter.convertTime(item.weatherModel.sys.sunrise)
            itemBinding.tvSunsetValue.text = TimeConverter.convertTime(item.weatherModel.sys.sunset)

            Glide
                .with(itemBinding.ivCityImage)
                .load(item.cityPhotoUrl)
                .error(R.drawable.ic_error)
                .centerCrop()
                .into(itemBinding.ivCityImage)
            }

    }

}