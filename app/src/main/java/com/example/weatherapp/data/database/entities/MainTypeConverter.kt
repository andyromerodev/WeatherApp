package com.example.weatherapp.data.database.entities

import androidx.room.TypeConverter
import com.example.weatherapp.data.model.Main
import com.google.gson.Gson

class MainTypeConverter {
    @TypeConverter
    fun fromMain(main: Main): String {
        return Gson().toJson(main)
    }

    @TypeConverter
    fun toMain(value: String): Main {
        return Gson().fromJson(value, Main::class.java)
    }
}