package com.example.weatherapp

import android.content.pm.ApplicationInfo
import android.os.Build
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import androidx.annotation.RequiresApi
import androidx.lifecycle.lifecycleScope
import com.example.weatherapp.core.WeatherService
import com.example.weatherapp.databinding.ActivityMainBinding
import com.example.weatherapp.model.Weather
import com.example.weatherapp.model.WeatherModel
import kotlinx.coroutines.DelicateCoroutinesApi
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch

class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val applicationInfo: ApplicationInfo =
            packageManager.getApplicationInfoCompat(application.packageName, 0)

        val apiKey = applicationInfo.metaData.getString("API_KEY")

        Log.d("APIKEY", apiKey.toString())

        lifecycleScope.launch {
            val weather = WeatherService()
            if (apiKey != null) {
               val weth = weather.getWeatherByCity("London", apiKey)
                binding.idTextView.text = weth.main.temp.toString()
                Log.d("WEATHER-TEMP", weth.main.temp.toString())
            }else{
                Log.d("ERROR", "ApiKey Error")
            }
        }




    }
}