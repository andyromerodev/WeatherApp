package com.example.weatherapp.domain.repository

import com.example.weatherapp.data.database.entities.WeatherEntity
import com.example.weatherapp.domain.model.WeatherModelOnDomain

interface WeatherRepository {

    suspend fun getWeatherFromApi(city: String, apiKey: String): WeatherModelOnDomain

    suspend fun getWeatherFromApiByCoordinates(
        latitude: Double,
        longitude: Double,
        apiKey: String
    ): WeatherModelOnDomain

    suspend fun getWeatherFromDataBase(cityName: String): WeatherModelOnDomain

    suspend fun getAllWeather(): List<WeatherModelOnDomain>

    suspend fun insertWeather(weather: WeatherEntity)
}