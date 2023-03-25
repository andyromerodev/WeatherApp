package com.example.weatherapp.data

import android.util.Log
import com.example.weatherapp.data.database.dao.WeatherDao
import com.example.weatherapp.data.database.entities.WeatherEntity
import com.example.weatherapp.data.model.WeatherModel
import com.example.weatherapp.data.model.WeatherProvider
import com.example.weatherapp.data.network.WeatherService
import com.example.weatherapp.domain.model.WeatherModelOnDomain
import com.example.weatherapp.domain.model.toDomain
import javax.inject.Inject

class WeatherRepository @Inject constructor(
    private val api: WeatherService,
    private val weatherDao: WeatherDao
) {

    //private val api = WeatherService()

    suspend fun getWeatherFromApi(city: String, apiKey: String): WeatherModelOnDomain {
        Log.d("TAGgetWeatherByCity", city)
        Log.d("TAGgetWeatherByCity", apiKey)

        val response: WeatherModel = api.getWeatherByCity(city, apiKey)

        Log.d("ERRORNULL", response.toDomain().toString())

        //WeatherProvider.resultWeatherProvider = response
        return response.toDomain()
    }

    suspend fun getWeatherFromApiByCoordinates(
        latitude: Double,
        longitude: Double,
        apiKey: String
    ): WeatherModelOnDomain {
        val response = api.getWeatherByCoordinates(latitude, longitude, apiKey)
        // WeatherProvider.resultWeatherProvider = response
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

    suspend fun getWeatherByCity(city: String, apiKey: String): WeatherModel {

        Log.d("TAGgetWeatherByCity", city)
        Log.d("TAGgetWeatherByCity", apiKey)

        val response = api.getWeatherByCity(city, apiKey)
        WeatherProvider.resultWeatherProvider = response
        return response
    }

    suspend fun getWeatherByCoordinates(
        latitude: Double,
        longitude: Double,
        apiKey: String
    ): WeatherModel {
        val response = api.getWeatherByCoordinates(latitude, longitude, apiKey)
        WeatherProvider.resultWeatherProvider = response
        return response
    }

}