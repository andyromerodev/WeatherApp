package com.example.weatherapp.ui.viewmodel

import android.util.Log
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.weatherapp.data.WeatherRepository
import com.example.weatherapp.data.model.WeatherModel
import com.example.weatherapp.data.model.WeatherProvider
import com.example.weatherapp.domain.GetAllWeatherUseCase
import com.example.weatherapp.domain.GetWeatherByCoordinates
import com.example.weatherapp.domain.GetWeatherUseCase
import com.example.weatherapp.domain.model.WeatherModelOnDomain
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.async
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import javax.inject.Inject
import javax.inject.Named

@HiltViewModel
class WeatherViewModel @Inject constructor(
    @Named("getWeatherUseCase") private val resultGetWeatherUseCase: GetWeatherUseCase,
    @Named("getWeatherByCoordinates") private val resultGetWeatherByCoordinates: GetWeatherByCoordinates,
    @Named("getAllWeatherUseCase") private val resultGetAllWeatherUseCase: GetAllWeatherUseCase,

    private val repository: WeatherRepository,
) : ViewModel() {

    val weatherModel = MutableLiveData<WeatherModelOnDomain>()
    val isLoading = MutableLiveData<Boolean>()
    val viewButton = MutableLiveData<Boolean>()
    val getListWeather = MutableLiveData<List<WeatherModelOnDomain>>()
    val sizeList = MutableLiveData<Int>()
    val cityViewModel: MutableLiveData<String> = MutableLiveData()
    val apiKeyViewModel: MutableLiveData<String> = MutableLiveData()
    val latitudeViewModel: MutableLiveData<Double> = MutableLiveData()
    val longitudeViewModel: MutableLiveData<Double> = MutableLiveData()


//    val apiKeyViewModel = MutableLiveData<String>()

    fun getWeatherByCity() {
        viewModelScope.launch {
            viewButton.postValue(false)
            isLoading.postValue(true)

            Log.d("CVM", cityViewModel.value.toString())
            Log.d("CVM", apiKeyViewModel.value.toString())

            val currentWeather =
                resultGetWeatherUseCase(
                    cityViewModel.value.toString(),
                    apiKeyViewModel.value.toString()
                )

            weatherModel.postValue(currentWeather)

            if (!resultGetWeatherUseCase.equals(0)) {
                isLoading.postValue(false)
                viewButton.postValue(true)
            }
        }
    }

    fun getWeatherByCoordinates() {
        viewModelScope.launch {
            viewButton.postValue(false)
            isLoading.postValue(true)

            val currentWeather = resultGetWeatherByCoordinates(
                latitudeViewModel.value?.toDouble() ?: 0.0,
                longitudeViewModel.value?.toDouble() ?: 0.0,
                apiKeyViewModel.value.toString()
            )

//            val currentWeather = WeatherProvider.resultWeatherProvider

            weatherModel.postValue(currentWeather)

            if (!currentWeather.equals(0)) {
                isLoading.postValue(false)
                viewButton.postValue(true)
            }
        }
    }

    fun getAllWeather() {
        viewModelScope.launch {
            val currentWeather = resultGetAllWeatherUseCase()

            getListWeather.postValue(currentWeather)
            sizeList.postValue(currentWeather.size)

            for (weather in currentWeather) {
                Log.d("LISTADEWEATHER", weather.name)
            }
        }
    }
}


