package com.example.weatherapp.domain

import com.example.weatherapp.data.WeatherRepository
import com.example.weatherapp.domain.model.WeatherModelOnDomain
import javax.inject.Inject

class GetAllWeatherUseCase @Inject constructor(private val repository: WeatherRepository) {

    suspend operator fun invoke(): List<WeatherModelOnDomain> {

        return repository.getAllWeather()
        
    }
}