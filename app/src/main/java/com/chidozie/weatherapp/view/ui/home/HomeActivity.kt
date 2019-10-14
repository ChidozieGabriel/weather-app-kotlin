package com.chidozie.weatherapp.view.ui.home

import android.os.Bundle
import android.util.Log
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import com.chidozie.weatherapp.R
import com.chidozie.weatherapp.api.ApiResponse
import com.chidozie.weatherapp.databinding.ActivityHomeBinding
import com.chidozie.weatherapp.factory.ViewModelFactory
import com.chidozie.weatherapp.models.Weather
import dagger.android.AndroidInjection
import javax.inject.Inject

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

  override fun onCreate(savedInstanceState: Bundle?) {
    AndroidInjection.inject(this)
    super.onCreate(savedInstanceState)

    viewModel.weatherFromNetwork.observe(this, Observer { processApiWeatherResponse(it) })
    viewModel.weather.observe(this, Observer { processWeather(it) })
  }

  private fun processWeather(weather: Weather) {
    Log.e("Weather", weather.toString())
    binding.city.text = weather.city.name
    val weatherDetail = weather.list[0]
    binding.humidityText.text = weatherDetail.main.humidity.toString()
    //    viewModel.imageUrl = "https://some-url/${weatherDetail.icon}" // TODO set using Glide
    binding.temperature.text = weatherDetail.main.temp.toString()
    binding.maxTemperature.text = weatherDetail.main.temp_max.toString()
    binding.minTemperature.text = weatherDetail.main.temp_min.toString()
    binding.windText.text = weatherDetail.wind.speed.toString()
  }

  private fun processApiWeatherResponse(apiResponse: ApiResponse<Weather>) {
    // TODO display progressBar
    Log.e("ApiResponse", apiResponse.code.toString())
    Log.e("ApiResponse", "error", apiResponse.error)
    if (apiResponse.isSuccessful) {
      apiResponse.body?.let {
        viewModel.weather.value = it
      }
    }

  }

}
