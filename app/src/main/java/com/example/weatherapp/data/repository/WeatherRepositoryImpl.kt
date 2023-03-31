package com.example.weatherapp.data.repository

import com.example.weatherapp.data.database.dao.WeatherDao
import com.example.weatherapp.data.database.entities.WeatherEntity
import com.example.weatherapp.data.model.WeatherModel
import com.example.weatherapp.data.network.WeatherService
import com.example.weatherapp.domain.model.WeatherModelOnDomain
import com.example.weatherapp.domain.model.toDomain
import com.example.weatherapp.domain.repository.WeatherRepository
import javax.inject.Inject

class WeatherRepositoryImpl @Inject constructor(
    private val api: WeatherService,
    private val weatherDao: WeatherDao
): WeatherRepository {

    override suspend fun getWeatherFromApi(city: String, apiKey: String): WeatherModelOnDomain {

        val response: WeatherModel = api.getWeatherByCity(city, apiKey)

        return response.toDomain()
    }

    override suspend fun getWeatherFromApiByCoordinates(
        latitude: Double,
        longitude: Double,
        apiKey: String
    ): WeatherModelOnDomain {
        val response = api.getWeatherByCoordinates(latitude, longitude, apiKey)
        return response.toDomain()
    }


    override suspend fun getWeatherFromDataBase(cityName: String): WeatherModelOnDomain {

        return try {
            val response = weatherDao.getWeather(cityName)
            response.toDomain()
        } catch (e: Exception) {
            WeatherModelOnDomain()
        }
    }


    override suspend fun getAllWeather(): List<WeatherModelOnDomain> {

        val response = weatherDao.getAllWeather()
        return response.map { it.toDomain() }

    }

    override suspend fun insertWeather(weather: WeatherEntity) {
        weatherDao.insertWeather(weather)
    }

}