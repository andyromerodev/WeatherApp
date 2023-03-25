package com.example.weatherapp.domain.model

import com.example.weatherapp.data.database.entities.WeatherEntity
import com.example.weatherapp.data.model.Main
import com.example.weatherapp.data.model.WeatherModel

data class WeatherModelOnDomain(
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

fun WeatherModel.toDomain() =
    WeatherModelOnDomain(base, cod, dt, id, main, name, timezone, visibility)

fun WeatherEntity.toDomain() =
    WeatherModelOnDomain(base, cod, dt, id, main, name, timezone, visibility)
