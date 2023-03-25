package com.example.weatherapp.domain

import android.util.Log
import com.example.weatherapp.data.WeatherRepository
import com.example.weatherapp.data.model.WeatherModel
import com.example.weatherapp.data.model.WeatherProvider
import javax.inject.Inject
import javax.inject.Named

class GetWeatherUseCaseWork @Inject constructor(
    @Named("city") private var city: String,
    @Named("apiKey") private var apiKey: String,
    private val repository: WeatherRepository,
) {

//    private val repository = WeatherRepository()

    suspend operator fun invoke(city: String, apiKey: String): WeatherModel {
        this.city = city
        this.apiKey = apiKey

        repository.getWeatherByCity(this.city, this.apiKey)
        return WeatherProvider.resultWeatherProvider
    }
}