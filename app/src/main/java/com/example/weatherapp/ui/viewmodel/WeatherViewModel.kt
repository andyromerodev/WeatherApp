package com.example.weatherapp.ui.viewmodel

import android.util.Log
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.weatherapp.data.model.WeatherModel
import com.example.weatherapp.data.model.WeatherProvider
import com.example.weatherapp.domain.GetWeatherByCoordinates
import com.example.weatherapp.domain.GetWeatherUseCase
import kotlinx.coroutines.launch

class WeatherViewModel : ViewModel() {

    val weatherModel = MutableLiveData<WeatherModel>()
    val isLoading = MutableLiveData<Boolean>()
//    val city = String
//    val apiKey = String

    fun getWeatherByCity(city: String, apiKey: String) {
        viewModelScope.launch {
            isLoading.postValue(true)

            //TODO Review this code
            val result = GetWeatherUseCase(city, apiKey)

            Log.d("DEBUGWEATHER", result.invoke().toString())
            val currentWeather = WeatherProvider.resultWeatherProvider
            Log.d("currentWeather", currentWeather.toString())

            weatherModel.postValue(currentWeather)

            if (!result.equals(0)) {
                isLoading.postValue(false)
            }
        }
    }

    fun getWeatherByCoordinates(latitude: Double, longitude: Double, apiKey: String) {
        viewModelScope.launch {
            isLoading.postValue(true)

            //TODO Review this code
            val result = GetWeatherByCoordinates(latitude, longitude, apiKey)

            Log.d("DEBUGWEATHER", result.invoke().toString())
            val currentWeather = WeatherProvider.resultWeatherProvider
            Log.d("currentWeather", currentWeather.toString())

            weatherModel.postValue(currentWeather)

            if (!result.equals(0)) {
                isLoading.postValue(false)
            }
        }
    }


    fun getWeatherByCountryVM() {
        val currentWeather = WeatherProvider.resultWeatherProvider
        weatherModel.postValue(currentWeather)
    }
}


