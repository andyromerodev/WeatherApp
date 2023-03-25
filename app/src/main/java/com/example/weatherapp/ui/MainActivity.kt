package com.example.weatherapp.ui

import android.Manifest
import android.content.pm.ApplicationInfo
import android.location.Location
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Toast
import androidx.activity.viewModels
import androidx.core.view.isVisible
import androidx.lifecycle.Observer
import androidx.lifecycle.lifecycleScope
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.weatherapp.R
import com.example.weatherapp.databinding.ActivityMainBinding
import com.example.weatherapp.getApplicationInfoCompat
import com.example.weatherapp.ui.recyclerview.WeatherAdapter
import com.example.weatherapp.ui.viewmodel.WeatherViewModel
import com.example.weatherapp.utils.GetLocation
import com.example.weatherapp.utils.GetPermission
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.launch

@AndroidEntryPoint
class MainActivity : AppCompatActivity(), androidx.appcompat.widget.SearchView.OnQueryTextListener,
    View.OnClickListener {

    private lateinit var binding: ActivityMainBinding

    private lateinit var apiKey: String

    private lateinit var adapter: WeatherAdapter

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
        initRecyclerView()

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

        weatherViewModel.viewButton.observe(this, Observer {
            binding.buttonGPS.isVisible = it
        })

    }

    override fun onQueryTextSubmit(query: String?): Boolean {

        weatherViewModel.cityViewModel.value = query.toString()
        weatherViewModel.apiKeyViewModel.value = apiKey
        weatherViewModel.getWeatherByCity()

        weatherViewModel.getAllWeather()
        weatherViewModel.getListWeather.observe(this) { weatherList ->
            adapter.updateData(weatherList)
        }
        adapter = WeatherAdapter(emptyList())
        val manager = LinearLayoutManager(this)
        binding.recyclerView.layoutManager = manager
        binding.recyclerView.adapter = adapter
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

                weatherViewModel.latitudeViewModel.value = latitude
                weatherViewModel.longitudeViewModel.value = longitude
                weatherViewModel.apiKeyViewModel.value = apiKey

                if (latitude != 0.0) {

//                    binding.loading.startAnimation(AnimationUtils.loadAnimation(this,
//                        R.anim.rotate_animation))

                    weatherViewModel.getWeatherByCoordinates()
                    //weatherViewModel.getAllWeather()

                } else {
                    Toast.makeText(
                        this,
                        "   Sin obtener las coordenadas \nVuelva a intentarlo o active el GPS",
                        Toast.LENGTH_LONG
                    ).show()
                }

            }
        } catch (e: Exception) {
            e.toString()
        }

    }


    private fun initRecyclerView() {
        weatherViewModel.getAllWeather()
        weatherViewModel.getListWeather.observe(this) { weatherList ->
            adapter.updateData(weatherList)
        }
        adapter = WeatherAdapter(emptyList())
        val manager = LinearLayoutManager(this)
        binding.recyclerView.layoutManager = manager
        binding.recyclerView.adapter = adapter
    }

}