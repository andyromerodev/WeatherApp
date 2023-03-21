package com.example.weatherapp.data.model

data class WeatherModel(
    val base: String = "",
    //val clouds: Clouds,
    val cod: Int = 0,
   // val coord: Coord,
    val dt: Int = 0,
    val id: Int = 0,
    val main: Main = Main(),
    val name: String = "",
   // val sys: Sys,
    val timezone: Int = 0,
    val visibility: Int = 0,
    //val weather: List<Weather>,
    //val wind: Wind
)