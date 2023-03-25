package com.example.weatherapp.ui.recyclerview

import android.annotation.SuppressLint
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.weatherapp.R
import com.example.weatherapp.domain.model.WeatherModelOnDomain
import kotlinx.coroutines.*

class WeatherAdapter(
    private var weatherListConst: List<WeatherModelOnDomain>
) : RecyclerView.Adapter<WeatherViewHolder>() {


    @SuppressLint("NotifyDataSetChanged")
    fun updateData(newWeatherList: List<WeatherModelOnDomain>) {
        weatherListConst = newWeatherList
        notifyDataSetChanged()
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): WeatherViewHolder {
        val layoutInflater = LayoutInflater.from(parent.context)
        return WeatherViewHolder(layoutInflater.inflate(R.layout.item_weather, parent, false))
    }

    override fun onBindViewHolder(holder: WeatherViewHolder, position: Int) {
        val item = weatherListConst[position]
        holder.render(item)
    }

    override fun getItemCount(): Int = weatherListConst.size
}
