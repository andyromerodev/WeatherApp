package com.example.weatherapp.data

import com.example.weatherapp.data.model.WeatherModel
import com.example.weatherapp.data.model.WeatherProvider
import com.example.weatherapp.data.network.WeatherService
import javax.inject.Inject

class WeatherRepository @Inject constructor(private val api: WeatherService) {

    //private val api = WeatherService()

    suspend fun getWeatherByCity(city: String, apiKey: String): WeatherModel {
        val response = api.getWeatherByCity(city,apiKey)
        WeatherProvider.resultWeatherProvider = response
        return response
    }

    suspend fun getWeatherByCoordinates(latitude: Double, longitude: Double, apiKey: String): WeatherModel {
        val response = api.getWeatherByCoordinates(latitude, longitude, apiKey)
        WeatherProvider.resultWeatherProvider = response
        return response
    }

}