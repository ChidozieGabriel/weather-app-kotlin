package com.chidozie.weatherapp.view.ui.home

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.chidozie.weatherapp.api.ApiResponse
import com.chidozie.weatherapp.models.Weather
import com.chidozie.weatherapp.repository.WeatherRepository
import com.chidozie.weatherapp.view.ui.BaseViewModel
import javax.inject.Inject

class HomeActivityViewModel @Inject constructor(
    private val weatherRepository: WeatherRepository
) : BaseViewModel() {
  val name: MutableLiveData<String> = MutableLiveData()
  var weather: MutableLiveData<Weather> = MutableLiveData()
  var weatherFromNetwork: LiveData<ApiResponse<Weather>> = MutableLiveData()
  var city: String = ""
  var temperature = 24.0.toString()
  var maxTemperature = 26.0.toString()
  var minTemperature = 22.0.toString()
  var humidity = 12.toString()
  var windSpeed = 10.0.toString()
  var imageUrl = ""

  init {
    loadFromDb()
    load()
  }

  private fun load() {
    weatherFromNetwork = weatherRepository.getWeathers(35.0, longitude = 139.0)
  }

  private fun loadFromDb() {
    // TODO load from database

  }

}