package com.chidozie.weatherapp.view.ui.home

import android.annotation.SuppressLint
import android.content.pm.PackageManager
import android.location.LocationManager
import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import android.util.Log
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import androidx.core.app.ActivityCompat
import androidx.core.content.ContextCompat
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import com.chidozie.weatherapp.R
import com.chidozie.weatherapp.databinding.ActivityHomeBinding
import com.chidozie.weatherapp.databinding.ItemWeatherBinding
import com.chidozie.weatherapp.factory.ViewModelFactory
import com.chidozie.weatherapp.models.GeoLocationDetail
import com.chidozie.weatherapp.models.GeoLocationGeometry
import com.chidozie.weatherapp.models.Weather
import com.chidozie.weatherapp.view.adapter.StateAdapter
import com.chidozie.weatherapp.view.getDayOfMonth
import com.chidozie.weatherapp.view.loadImage
import com.chidozie.weatherapp.view.toFixed1
import com.chidozie.weatherapp.view.viewholder.StateViewHolder
import dagger.android.AndroidInjection
import java.util.*
import javax.inject.Inject

const val MY_LOCATION_PERMISSION = 0

class HomeActivity : AppCompatActivity() {

  @Inject
  lateinit var viewModelFactory: ViewModelFactory

  private val binding by lazy {
    DataBindingUtil.setContentView<ActivityHomeBinding>(
      this,
      R.layout.activity_home
    )
  }

  private val viewModel by lazy {
    ViewModelProviders.of(this, viewModelFactory)
      .get(HomeActivityViewModel::class.java)
  }

  override fun onRequestPermissionsResult(
    requestCode: Int,
    permissions: Array<String>, grantResults: IntArray
  ) {
    when (requestCode) {
      MY_LOCATION_PERMISSION -> {
        // If request is cancelled, the result arrays are empty.
        if ((grantResults.isNotEmpty() && grantResults[0] == PackageManager.PERMISSION_GRANTED)) {
          // permission was granted, yay!
          setLocation()
        } else {
          // permission denied, boo! Disable the
          // functionality that depends on this permission.
        }
        return
      }

      // Add other 'when' lines to check for other
      // permissions this app might request.
      else -> {
        // Ignore all other requests.
      }
    }
  }


  override fun onCreate(savedInstanceState: Bundle?) {
    AndroidInjection.inject(this)
    super.onCreate(savedInstanceState)
    binding

    if (
      ContextCompat.checkSelfPermission(
        this,
        android.Manifest.permission.ACCESS_COARSE_LOCATION
      ) != PackageManager.PERMISSION_GRANTED
      ||
      ContextCompat.checkSelfPermission(
        this,
        android.Manifest.permission.ACCESS_FINE_LOCATION
      ) != PackageManager.PERMISSION_GRANTED
    ) {
      ActivityCompat.requestPermissions(
        this,
        arrayOf(
          android.Manifest.permission.ACCESS_COARSE_LOCATION,
          android.Manifest.permission.ACCESS_FINE_LOCATION
        ),
        MY_LOCATION_PERMISSION
      )
    } else {
      setLocation()
    }

    loadWeatherFromDb()
    observe()

    viewModel.adapter = StateAdapter(object : StateViewHolder.ClickListener {
      override fun onItemClick(geoLocationDetail: GeoLocationDetail, view: View) {
        fetchWeather(geoLocationDetail.geometry)
        binding.search.text = null
      }
    })
    binding.statesRecycler.adapter = viewModel.adapter
    binding.search.addTextChangedListener(object : TextWatcher {

      override fun afterTextChanged(s: Editable) {}

      override fun beforeTextChanged(
        s: CharSequence, start: Int,
        count: Int, after: Int
      ) {
      }

      override fun onTextChanged(
        s: CharSequence, start: Int,
        before: Int, count: Int
      ) {
        if (count > 2 && s.trim().length > 2) searchLocation(s.toString())
        else viewModel.adapter.updateItems(listOf())
      }
    })
  }

  private fun fetchWeather(geoLocationGeometry: GeoLocationGeometry) {
    viewModel.fetchWeather(geoLocationGeometry.lat, geoLocationGeometry.lng)
      .observe(this, Observer {
        // TODO display progressBar
        Log.e("ApiResponse", it.code.toString())
        Log.e("ApiResponse", "error", it.error)
        if (it.isSuccessful) {
          it.body?.let { weather ->
            viewModel.weather.value = weather
            viewModel.saveWeatherToDb(weather)
          }
        } else {
          loadWeatherFromDb()
        }
      })
  }

  private fun loadWeatherFromDb() {
    viewModel.loadWeatherFromDb().observe(this, Observer {
      Log.e("Weather History", it.toString())
      if (!it.isNullOrEmpty()) {
        val lastWeather = it[it.lastIndex]
        Log.e("Weather History Each", lastWeather.toString())
        viewModel.weather.value = lastWeather
      } else {
        // set no data
      }
    })
  }

  private fun searchLocation(search: String) {
    viewModel.searchLocation(search).observe(this, Observer {
      // TODO display progressBar
      Log.e("ApiResponse Geo", it.code.toString())
      Log.e("ApiResponse Geo", "error", it.error)
      if (it.isSuccessful) {
        it.body?.let { geoLocation ->
          viewModel.geoLocation.value = geoLocation
        }
      } else {
        // display no location found
      }
    })
  }

  private fun observe() {
    observeGeoLocation()
    observeWeather()
  }

  private fun observeGeoLocation() {
    viewModel.geoLocation.observe(this, Observer {
      Log.e("GeoLocation", it.toString())
      viewModel.adapter.updateItems(it.results)
    })
  }

  @SuppressLint("SetTextI18n")
  private fun observeWeather() {
    viewModel.weather.observe(this, Observer {
      Log.e("Weather", it.toString())
      binding.city.text = it.city.name
      val weatherDetail = it.list[0]
      binding.day.text = Date().getDayOfMonth()
      binding.humidityText.text = "${weatherDetail.main.humidity.toFixed1()} %"
      binding.temperature.text = weatherDetail.main.getTemp()
      binding.maxTemperature.text = weatherDetail.main.getTempMax()
      binding.minTemperature.text = weatherDetail.main.getTempMin()
      binding.windText.text = "${weatherDetail.wind.speed.toFixed1()} m/s"
      val weatherDescription = weatherDetail.weather[0]
      binding.description.text = weatherDescription.description
      binding.cloudImage.loadImage(weatherDescription.getImageUrl())

      processEachWeather(binding.one, it, 1)
      processEachWeather(binding.two, it, 2)
      processEachWeather(binding.three, it, 3)
      processEachWeather(binding.four, it, 4)
      processEachWeather(binding.five, it, 5)
    })
  }

  @SuppressLint("SetTextI18n")
  fun processEachWeather(binding: ItemWeatherBinding, weather: Weather, n: Int) {
    val weatherDetail = weather.list[n]
    binding.day.text = weatherDetail.getDate().getDayOfMonth(n).substring(0, 3)
    binding.temperature.text = weatherDetail.main.getTemp()
    binding.image.loadImage(weatherDetail.weather[0].getImageUrl())
  }

  @SuppressLint("MissingPermission")
  private fun setLocation() {
    val locationManager = getSystemService(LOCATION_SERVICE) as LocationManager?
    val location = locationManager?.getLastKnownLocation(LocationManager.GPS_PROVIDER)
    location?.let { viewModel.fetchWeather(it.latitude, it.longitude) }
  }

}

