package com.example.cat_weather.views.adapters

import android.annotation.SuppressLint
import android.view.LayoutInflater
import android.view.MotionEvent
import android.view.View
import android.view.ViewGroup
import androidx.core.view.isVisible
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.cat_weather.R
import com.example.cat_weather.databinding.ItemForecastBinding
import com.example.cat_weather.models.forecastmodel.WeatherForecast
import com.example.cat_weather.utils.TemperatureConverter
import timber.log.Timber

class ForecastAdapter(private val forecast: Map<String, List<WeatherForecast>>): RecyclerView.Adapter<ForecastAdapter.ForecastViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ForecastViewHolder {
        val itemBinding = ItemForecastBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return ForecastViewHolder(itemBinding)
    }

    override fun onBindViewHolder(holder: ForecastViewHolder, position: Int) {
        val key = keyKeyForIndex(position)
        holder.bind(key, forecast[key]!!)
    }

    override fun getItemCount(): Int {
        return forecast.size
    }

    private fun keyKeyForIndex(index: Int): String {
        return forecast.keys.toList()[index]
    }

    class ForecastViewHolder(private val itemBinding: ItemForecastBinding): RecyclerView.ViewHolder(itemBinding.root) {

        @SuppressLint("ClickableViewAccessibility")
        fun bind(day: String, data: List<WeatherForecast>) {
            Timber.d("Day: $day")

            itemBinding.tvForecastDay.text = day
            itemBinding.tvForecastFeelsLike.text = itemView.resources.getString(R.string.celsius_sign, TemperatureConverter.convertKelvinToCelsius(data[0].main.feels_like))
            itemBinding.tvForecastHumidity.text = data[0].main.humidity.toString()
            itemBinding.tvForecastMaxTemp.text = itemView.resources.getString(R.string.celsius_sign, TemperatureConverter.convertKelvinToCelsius(data[0].main.temp_max))
            itemBinding.tvForecastMinTemp.text = itemView.resources.getString(R.string.celsius_sign, TemperatureConverter.convertKelvinToCelsius(data[0].main.temp_min))
            itemBinding.tvForecastSunrise.text = 0.toString()
            itemBinding.tvForecastSunset.text = 0.toString()

            itemBinding.rvHourRecycler.apply {
                layoutManager = LinearLayoutManager(
                    itemView.context, RecyclerView.HORIZONTAL, false
                )
                adapter = ForecastHourAdapter(data)
                setHasFixedSize(true)
            }

            itemBinding.ivForecastShowMoreOrLess.setOnClickListener {
                if (itemBinding.clForecastForecastLayout.isVisible) {
                    itemBinding.clForecastForecastLayout.visibility = View.GONE
                    itemBinding.ivForecastShowMoreOrLess.setImageResource(R.drawable.ic_less_button)
                } else {
                    itemBinding.clForecastForecastLayout.visibility = View.VISIBLE
                    itemBinding.ivForecastShowMoreOrLess.setImageResource(R.drawable.ic_more_button)
                }
            }

            itemBinding.rvHourRecycler.setOnTouchListener { view, event ->
                if (event.actionMasked == MotionEvent.ACTION_UP) {
                    itemBinding.root.parent.requestDisallowInterceptTouchEvent(false)
                }
                else {
                    itemBinding.root.parent.requestDisallowInterceptTouchEvent(true)
                }
                view.onTouchEvent(event)
            }
        }

    }

}