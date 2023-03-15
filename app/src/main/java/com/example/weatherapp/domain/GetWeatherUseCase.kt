package com.example.weatherapp.domain

import com.example.weatherapp.data.WeatherRepository
import com.example.weatherapp.data.model.WeatherModel
import com.example.weatherapp.data.model.WeatherProvider

class GetWeatherUseCase(private var city: String, private var apiKey: String) {

    private val repository = WeatherRepository()

    suspend operator fun invoke(): WeatherModel {
        repository.getWeatherByCity(city, apiKey)
        return WeatherProvider.resultWeatherProvider
    }



}