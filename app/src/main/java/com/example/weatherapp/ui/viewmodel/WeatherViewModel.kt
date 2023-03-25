package com.example.weatherapp.ui.viewmodel

import android.util.Log
import androidx.lifecycle.LiveData
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

    private val _weatherModel = MutableLiveData<WeatherModelOnDomain>()
    val weatherModel: LiveData<WeatherModelOnDomain> = _weatherModel

    private val _isLoading = MutableLiveData<Boolean>()
    val isLoading: LiveData<Boolean> = _isLoading

    private val _viewButton = MutableLiveData<Boolean>()
    val viewButton: LiveData<Boolean> = _viewButton

    private val _getListWeather = MutableLiveData<List<WeatherModelOnDomain>>()
    val getListWeather: LiveData<List<WeatherModelOnDomain>> = _getListWeather

    private val _sizeList = MutableLiveData<Int>()
    val sizeList: LiveData<Int> = _sizeList

    private val _cityViewModel: MutableLiveData<String> = MutableLiveData()
    val cityViewModel: LiveData<String> = _cityViewModel

    private val _apiKeyViewModel: MutableLiveData<String> = MutableLiveData()
    val apiKeyViewModel: LiveData<String> = _apiKeyViewModel

    private val _latitudeViewModel: MutableLiveData<Double> = MutableLiveData()
    val latitudeViewModel: LiveData<Double> = _latitudeViewModel

    private val _longitudeViewModel: MutableLiveData<Double> = MutableLiveData()
    val longitudeViewModel: LiveData<Double> = _longitudeViewModel


    fun updateWeatherModel(weatherModel: WeatherModelOnDomain) {
        _weatherModel.value = weatherModel
    }

    fun updateIsLoading(isLoading: Boolean) {
        _isLoading.value = isLoading
    }

    fun updateViewButton(viewButton: Boolean) {
        _viewButton.value = viewButton
    }

    fun updateGetListWeather(getListWeather: List<WeatherModelOnDomain>) {
        _getListWeather.value = getListWeather
    }

    fun updateSizeList(sizeList: Int) {
        _sizeList.value = sizeList
    }

    fun updateCityViewModel(city: String) {
        _cityViewModel.value = city
    }

    fun updateApiKeyViewModel(apiKey: String) {
        _apiKeyViewModel.value = apiKey
    }

    fun updateLatitudeViewModel(latitude: Double) {
        _latitudeViewModel.value = latitude
    }

    fun updateLongitudeViewModel(longitude: Double) {
        _longitudeViewModel.value = longitude
    }

//    val isLoading = MutableLiveData<Boolean>()
//    val viewButton = MutableLiveData<Boolean>()
//    val getListWeather = MutableLiveData<List<WeatherModelOnDomain>>()
//    val sizeList = MutableLiveData<Int>()
//    val cityViewModel: MutableLiveData<String> = MutableLiveData()
//    val apiKeyViewModel: MutableLiveData<String> = MutableLiveData()
//    val latitudeViewModel: MutableLiveData<Double> = MutableLiveData()
//    val longitudeViewModel: MutableLiveData<Double> = MutableLiveData()


//    val apiKeyViewModel = MutableLiveData<String>()

    fun getWeatherByCity() {
        viewModelScope.launch {
            _viewButton.postValue(false)
            _isLoading.postValue(true)

            Log.d("CVM", cityViewModel.value.toString())
            Log.d("CVM", apiKeyViewModel.value.toString())

            val currentWeather =
                resultGetWeatherUseCase(
                    cityViewModel.value.toString(),
                    apiKeyViewModel.value.toString()
                )

            _weatherModel.postValue(currentWeather)

            if (!resultGetWeatherUseCase.equals(0)) {
                _isLoading.postValue(false)
                _viewButton.postValue(true)
            }
        }
    }

    fun getWeatherByCoordinates() {
        viewModelScope.launch {
            _viewButton.postValue(false)
            _isLoading.postValue(true)

            val currentWeather = resultGetWeatherByCoordinates(
                latitudeViewModel.value?.toDouble() ?: 0.0,
                longitudeViewModel.value?.toDouble() ?: 0.0,
                apiKeyViewModel.value.toString()
            )

//            val currentWeather = WeatherProvider.resultWeatherProvider

            _weatherModel.postValue(currentWeather)

            if (!currentWeather.equals(0)) {
                _isLoading.postValue(false)
                _viewButton.postValue(true)
            }
        }
    }

    fun getAllWeather() {
        viewModelScope.launch {
            val currentWeather = resultGetAllWeatherUseCase()

            _getListWeather.postValue(currentWeather)
            _sizeList.postValue(currentWeather.size)

            for (weather in currentWeather) {
                Log.d("LISTADEWEATHER", weather.name)
            }
        }
    }
}


