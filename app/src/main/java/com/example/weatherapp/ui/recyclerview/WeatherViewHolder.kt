package com.example.weatherapp.ui.recyclerview

import android.view.View
import androidx.recyclerview.widget.RecyclerView
import com.example.weatherapp.databinding.ItemWeatherBinding
import com.example.weatherapp.domain.model.WeatherModelOnDomain

class WeatherViewHolder(view: View): RecyclerView.ViewHolder(view) {

    private val binding = ItemWeatherBinding.bind(view)


    fun render(weatherModelOnDomain: WeatherModelOnDomain){
        binding.idCity.text = weatherModelOnDomain.name
        binding.idTVTemp.text = weatherModelOnDomain.main.temp.toString()
        binding.idTVTempMax.text = weatherModelOnDomain.main.temp_max.toString()
        binding.idTVTempMin.text = weatherModelOnDomain.main.temp_min.toString()
    }


}
