package com.example.weatherapp.domain

import com.example.weatherapp.data.WeatherRepository
import com.example.weatherapp.data.database.entities.toDatabase
import com.example.weatherapp.domain.model.WeatherModelOnDomain
import javax.inject.Inject
import javax.inject.Named

class GetWeatherUseCase @Inject constructor(
    @Named("city") private var city: String,
    @Named("apiKey") private var apiKey: String,
    private val repository: WeatherRepository,
) {

    suspend operator fun invoke(city: String, apiKey: String): WeatherModelOnDomain {
        this.city = city
        this.apiKey = apiKey

        val weather = repository.getWeatherFromApi(this.city, this.apiKey)

        return if (weather.name != "") {
            repository.insertWeather(weather.toDatabase())
            weather
        } else {
            repository.getWeatherFromDataBase(this.city)
        }
    }
}