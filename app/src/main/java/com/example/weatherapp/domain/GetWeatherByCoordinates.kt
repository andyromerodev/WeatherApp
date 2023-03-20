package com.example.weatherapp.domain

import com.example.weatherapp.data.WeatherRepository
import com.example.weatherapp.data.model.WeatherModel
import com.example.weatherapp.data.model.WeatherProvider

class GetWeatherByCoordinates(
    private var latitude: Double,
    private var longitude: Double,
    private var apiKey: String,
    private val repository: WeatherRepository,
) {

    //private val repository = WeatherRepository()

    suspend operator fun invoke(): WeatherModel {
        repository.getWeatherByCoordinates(latitude, longitude, apiKey)
        return WeatherProvider.resultWeatherProvider
    }
}