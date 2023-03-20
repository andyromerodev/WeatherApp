package com.example.weatherapp.ui

import android.Manifest
import android.app.Instrumentation.ActivityResult
import android.content.pm.ApplicationInfo
import android.location.Location
import android.os.Build
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.SearchView
import android.widget.Toast
import androidx.activity.result.contract.ActivityResultContracts
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
import com.example.weatherapp.utils.GetLocation
import com.example.weatherapp.utils.GetPermission
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.launch
import okhttp3.internal.connection.Exchange

@AndroidEntryPoint
class MainActivity : AppCompatActivity(), androidx.appcompat.widget.SearchView.OnQueryTextListener,
    View.OnClickListener {

    private lateinit var binding: ActivityMainBinding

    private lateinit var apiKey: String

    private val weatherViewModel: WeatherViewModel by viewModels()

    private val getPermission = GetPermission(this, Manifest.permission.ACCESS_COARSE_LOCATION)

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        binding = ActivityMainBinding.inflate(layoutInflater)
        binding.idSearchView.setOnQueryTextListener(this)
        binding.buttonGPS.setOnClickListener(this)
        binding.loading.isVisible = false
        setContentView(binding.root)

        val applicationInfo: ApplicationInfo =
            packageManager.getApplicationInfoCompat(application.packageName, 0)

        apiKey = applicationInfo.metaData.getString("API_KEY").toString()

        weatherViewModel.weatherModel.observe(this, Observer {
            binding.idTVTemp.text = it.main.temp.toString()
            binding.idTVTempMin.text = it.main.temp_min.toString()
            binding.idTVTempMax.text = it.main.temp_max.toString()
            binding.idSearchView.onActionViewExpanded()
            binding.idSearchView.clearFocus()
            binding.idSearchView.setQuery(it.name, false)
        })

        weatherViewModel.isLoading.observe(this, Observer {
            binding.loading.isVisible = it
        })

    }

    override fun onQueryTextSubmit(query: String?): Boolean {

        weatherViewModel.cityViewModel.value = query.toString()
        weatherViewModel.apiKeyViewModel.value = apiKey
        weatherViewModel.getWeatherByCity()
        return true
    }

    override fun onQueryTextChange(p0: String?): Boolean {
        return true
    }

    override fun onClick(view: View?) {

        getPermission.runPermissionLocation()

        try {
            val locationGet = GetLocation()
            val lastLocation = locationGet.getLocation(this)
            lastLocation?.addOnSuccessListener { locationgps: Location? ->
                val latitude = locationgps?.latitude ?: 0.0
                val longitude = locationgps?.longitude ?: 0.0

                if (latitude != 0.0){
                    weatherViewModel.getWeatherByCoordinates(latitude, longitude, apiKey)
                }else{
                    Toast.makeText(this,"Sin obtener las coordenadas - Vuelva a intentarlo", Toast.LENGTH_SHORT).show()
                }

            }
        } catch (e: Exception) {
            e.toString()
        }

    }

}