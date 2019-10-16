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
import com.chidozie.weatherapp.api.ApiResponse
import com.chidozie.weatherapp.databinding.ActivityHomeBinding
import com.chidozie.weatherapp.databinding.ItemWeatherBinding
import com.chidozie.weatherapp.factory.ViewModelFactory
import com.chidozie.weatherapp.models.GeoLocation
import com.chidozie.weatherapp.models.GeoLocationDetail
import com.chidozie.weatherapp.models.Weather
import com.chidozie.weatherapp.models.WeatherDetail
import com.chidozie.weatherapp.view.adapter.StateAdapter
import com.chidozie.weatherapp.view.getDayOfMonth
import com.chidozie.weatherapp.view.loadImage
import com.chidozie.weatherapp.view.toFixed1
import com.chidozie.weatherapp.view.viewholder.StateViewHolder
import dagger.android.AndroidInjection
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

    if (ContextCompat.checkSelfPermission(
        this,
        android.Manifest.permission.ACCESS_COARSE_LOCATION
      ) != PackageManager.PERMISSION_GRANTED
    ) {
      ActivityCompat.requestPermissions(
        this,
        arrayOf(android.Manifest.permission.ACCESS_COARSE_LOCATION),
        MY_LOCATION_PERMISSION
      )
    } else {
      setLocation()
    }

    observe()
    viewModel.adapter = StateAdapter(object : StateViewHolder.ClickListener {
      override fun onItemClick(geoLocationDetail: GeoLocationDetail, view: View) {
        viewModel.load(geoLocationDetail.geometry.lat, geoLocationDetail.geometry.lng)
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
        if (count > 2 && s.trim().length > 2) viewModel.search(s.toString())
        else viewModel.adapter.updateItems(listOf())
      }
    })
  }

  private fun observe() {
    viewModel.searchLocation.observe(this, Observer { processApiGeoLocationResponse(it) })
    viewModel.geoLocation.observe(this, Observer { processGeoLocation(it) })
    viewModel.weatherFromNetwork.observe(this, Observer { processApiWeatherResponse(it) })
    viewModel.weatherFromDb.observe(this, Observer { processWeatherDb(it) })
    viewModel.weather.observe(this, Observer { processWeather(it) })
  }

  @SuppressLint("MissingPermission")
  private fun setLocation() {
    val locationManager = getSystemService(LOCATION_SERVICE) as LocationManager?
    val location = locationManager?.getLastKnownLocation(LocationManager.GPS_PROVIDER)
    location?.let { viewModel.load(it.latitude, it.longitude) }
  }

  @SuppressLint("SetTextI18n")
  private fun processGeoLocation(geoLocation: GeoLocation) {
    Log.e("GeoLocation", geoLocation.toString())
    viewModel.adapter.updateItems(geoLocation.results)
  }

  private fun processApiGeoLocationResponse(apiResponse: ApiResponse<GeoLocation>) {
    // TODO display progressBar
    Log.e("ApiResponse Geo", apiResponse.code.toString())
    Log.e("ApiResponse Geo", "error", apiResponse.error)
    if (apiResponse.isSuccessful) {
      apiResponse.body?.let {
        viewModel.geoLocation.value = it
      }
    }
  }

  @SuppressLint("SetTextI18n")
  private fun processWeather(weather: Weather) {
    Log.e("Weather", weather.toString())
    binding.city.text = weather.city.name
    val weatherDetail = weather.list[0]
    binding.day.text = weatherDetail.getDate().getDayOfMonth()
    binding.humidityText.text = weatherDetail.main.humidity.toString()
    binding.temperature.text = "${weatherDetail.main.temp.toFixed1()} C"
    binding.maxTemperature.text = "max: ${weatherDetail.main.temp_max.toFixed1()} C"
    binding.minTemperature.text = "min: ${weatherDetail.main.temp_min.toFixed1()} C"
    binding.windText.text = "${weatherDetail.wind.speed.toFixed1()} m/s"
    val weatherDescription = weatherDetail.weather[0]
    binding.description.text = weatherDescription.description
    binding.cloudImage.loadImage(weatherDescription.getImageUrl())

    processEach(binding.one, weather.list[1])
    processEach(binding.two, weather.list[2])
    processEach(binding.three, weather.list[3])
    processEach(binding.four, weather.list[4])
    processEach(binding.five, weather.list[5])
  }

  @SuppressLint("SetTextI18n")
  fun processEach(binding: ItemWeatherBinding, weatherDetail: WeatherDetail) {
    binding.day.text = weatherDetail.getDate().getDayOfMonth().substring(0, 2)
    binding.temperature.text = "${weatherDetail.main.temp.toFixed1()} C"
    binding.image.loadImage(weatherDetail.weather[0].getImageUrl())
  }

  private fun processApiWeatherResponse(apiResponse: ApiResponse<Weather>) {
    // TODO display progressBar
    Log.e("ApiResponse", apiResponse.code.toString())
    Log.e("ApiResponse", "error", apiResponse.error)
    if (apiResponse.isSuccessful) {
      apiResponse.body?.let {
        viewModel.weather.value = it
      }
    } else {
      viewModel.loadFromDb()
    }
  }


  private fun processWeatherDb(weatherHistory: List<Weather>) {
    Log.e("Weather History", weatherHistory.toString())
    if (weatherHistory.isNullOrEmpty()) {
      // set no Data
    }

    val lastWeather = weatherHistory[weatherHistory.lastIndex]
    Log.e("Weather History Each", lastWeather.toString())

    viewModel.weather.value = lastWeather
  }

}
