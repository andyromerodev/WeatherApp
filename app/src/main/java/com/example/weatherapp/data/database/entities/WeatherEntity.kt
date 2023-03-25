package com.example.weatherapp.data.database.entities

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import androidx.room.TypeConverters
import com.example.weatherapp.data.model.Main
import com.example.weatherapp.domain.model.WeatherModelOnDomain

@Entity(tableName = "weather_table")
@TypeConverters(MainTypeConverter::class)
data class WeatherEntity(

    @ColumnInfo("base") val base: String,
    //val clouds: Clouds,
    @ColumnInfo("cod") val cod: Int,
    // val coord: Coord,
    @ColumnInfo("dt") val dt: Int,
    @PrimaryKey(autoGenerate = true)
    @ColumnInfo("id") val id: Int,
    @ColumnInfo("main") val main: Main,
    @ColumnInfo(name = "name") val name: String,
    // val sys: Sys,
    @ColumnInfo("timezone") val timezone: Int,
    @ColumnInfo("visibility") val visibility: Int,
    //val weather: List<Weather>,
    //val wind: Wind
)

fun WeatherModelOnDomain.toDatabase() =
    WeatherEntity(
        base = base,
        cod = cod,
        dt = dt,
        id = id,
        main = main,
        name = name,
        timezone = timezone,
        visibility = visibility
    )
