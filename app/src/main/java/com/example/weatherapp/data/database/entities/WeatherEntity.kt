package com.example.weatherapp.data.database.entities

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import com.example.weatherapp.data.model.Main

@Entity(tableName = "TABLE_NAME")
data class WeatherEntity(
    @PrimaryKey(autoGenerate = true)
    @ColumnInfo("base")val base: String,
    //val clouds: Clouds,
    @ColumnInfo("cod") val cod: Int,
    // val coord: Coord,
    @ColumnInfo("dt") val dt: Int,
    @ColumnInfo("id") val id: Int,
    @ColumnInfo("main") val main: Main,
    @ColumnInfo("name") val name: String,
    // val sys: Sys,
    @ColumnInfo("timezone") val timezone: Int,
    @ColumnInfo("visibility") val visibility: Int,
    //val weather: List<Weather>,
    //val wind: Wind
)
