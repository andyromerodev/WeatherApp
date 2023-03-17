package com.example.weatherapp.data

import com.example.weatherapp.data.model.WeatherModel
import com.example.weatherapp.data.model.WeatherProvider
import com.example.weatherapp.data.network.WeatherService

class WeatherRepository {

    private val api = WeatherService()

    suspend fun getWeatherByCity(city: String, apiKey: String): WeatherModel {
        val response = api.getWeatherByCity(city,apiKey)
        WeatherProvider.resultWeatherProvider = response
        return response
    }

    suspend fun getWeatherByLatLong(city: String, apiKey: String): WeatherModel {
        val response = api.getWeatherByLatLong(city,apiKey)
        WeatherProvider.resultWeatherProvider = response
        return response
    }

}