package com.example.weatherapp.data.network

import com.example.weatherapp.data.model.WeatherModel
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Query

interface WeatherApiClient {

    @GET("weather")
    suspend fun getWeather(
        @Query("q") city: String,
        @Query("appid") apiKey: String,
        @Query("units") metric: String = "metric",
    ): Response<WeatherModel>

    @GET("weather")
    suspend fun getWeatherLatLong(
        @Query("lat") lat: Double,
        @Query("lon") lon: Double,
        @Query("appid") apiKey: String,
        @Query("units") metric: String = "metric",
    ): Response<WeatherModel>

}