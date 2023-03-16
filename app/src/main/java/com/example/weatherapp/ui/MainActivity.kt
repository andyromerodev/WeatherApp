package com.example.weatherapp.ui

import android.content.pm.ApplicationInfo
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.SearchView
import androidx.activity.viewModels
import androidx.core.view.isVisible
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModel
import androidx.lifecycle.lifecycleScope
import com.example.weatherapp.R
import com.example.weatherapp.data.model.WeatherModel
import com.example.weatherapp.data.network.WeatherService
import com.example.weatherapp.databinding.ActivityMainBinding
import com.example.weatherapp.getApplicationInfoCompat
import com.example.weatherapp.ui.viewmodel.WeatherViewModel
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.launch

@AndroidEntryPoint
class MainActivity : AppCompatActivity(), androidx.appcompat.widget.SearchView.OnQueryTextListener {

    private lateinit var binding: ActivityMainBinding

    private lateinit var apiKey: String

    private val weatherViewModel: WeatherViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        binding = ActivityMainBinding.inflate(layoutInflater)
        binding.idSearchView.setOnQueryTextListener(this)
        setContentView(binding.root)

        val applicationInfo: ApplicationInfo =
            packageManager.getApplicationInfoCompat(application.packageName, 0)

        apiKey = applicationInfo.metaData.getString("API_KEY").toString()

        Log.d("APIKEY", apiKey.toString())

        weatherViewModel.weatherModel.observe(this, Observer{
            binding.idTVTemp.text = it.main.temp.toString()
            binding.idTVTempMin.text = it.main.temp_min.toString()
            binding.idTVTempMax.text = it.main.temp_max.toString()
        })

        binding.loading.isVisible = false

        weatherViewModel.isLoading.observe(this, Observer{
            binding.loading.isVisible = it
        })
//        lifecycleScope.launch {
//            val weather = WeatherService()
//            if (apiKey != null) {
//                val weth = weather.getWeatherByCity("London", apiKey)
//                binding.idTVTemp.text = weth.main.temp.toString()
//                Log.d("WEATHER-TEMP", weth.main.temp.toString())
//            } else {
//                Log.d("ERROR", "ApiKey Error")
//            }
//        }

    }

    override fun onQueryTextSubmit(query: String?): Boolean {
//        weatherViewModel.city.postValue(query.orEmpty())
//        weatherViewModel.apiKey.postValue(apiKey)
        weatherViewModel.onCreate(query.toString(), apiKey)
        return true
    }

    override fun onQueryTextChange(p0: String?): Boolean {
        return true
    }

}