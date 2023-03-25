package com.example.weatherapp.data

import com.example.weatherapp.data.database.dao.WeatherDao
import com.example.weatherapp.data.database.entities.WeatherEntity
import com.example.weatherapp.data.model.WeatherModel
import com.example.weatherapp.data.network.WeatherService
import com.example.weatherapp.domain.model.WeatherModelOnDomain
import com.example.weatherapp.domain.model.toDomain
import javax.inject.Inject

class WeatherRepository @Inject constructor(
    private val api: WeatherService,
    private val weatherDao: WeatherDao
) {

    suspend fun getWeatherFromApi(city: String, apiKey: String): WeatherModelOnDomain {

        val response: WeatherModel = api.getWeatherByCity(city, apiKey)

        return response.toDomain()
    }

    suspend fun getWeatherFromApiByCoordinates(
        latitude: Double,
        longitude: Double,
        apiKey: String
    ): WeatherModelOnDomain {
        val response = api.getWeatherByCoordinates(latitude, longitude, apiKey)
        return response.toDomain()
    }


    suspend fun getWeatherFromDataBase(cityName: String): WeatherModelOnDomain {

        return try {
            val response = weatherDao.getWeather(cityName)
            response.toDomain()
        } catch (e: Exception) {
            WeatherModelOnDomain()
        }
    }


    suspend fun getAllWeather(): List<WeatherModelOnDomain> {

        val response = weatherDao.getAllWeather()
        return response.map { it.toDomain() }

    }

    suspend fun insertWeather(weather: WeatherEntity) {
        weatherDao.insertWeather(weather)
    }

}