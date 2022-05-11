package com.example.cat_weather.views

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.example.cat_weather.databinding.FragmentWeatherViewBinding

class WeatherView : Fragment() {

    private var _binding: FragmentWeatherViewBinding? = null
    private val binding: FragmentWeatherViewBinding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentWeatherViewBinding.inflate(inflater, container, false)
        return binding.root
    }

}