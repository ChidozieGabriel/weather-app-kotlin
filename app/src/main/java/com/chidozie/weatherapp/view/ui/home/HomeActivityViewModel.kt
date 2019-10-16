package com.chidozie.weatherapp.view.ui.home

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.chidozie.weatherapp.api.ApiResponse
import com.chidozie.weatherapp.models.GeoLocation
import com.chidozie.weatherapp.models.Weather
import com.chidozie.weatherapp.repository.GeoLocationRepository
import com.chidozie.weatherapp.repository.WeatherRepository
import com.chidozie.weatherapp.view.adapter.StateAdapter
import com.chidozie.weatherapp.view.ui.BaseViewModel
import javax.inject.Inject

class HomeActivityViewModel @Inject constructor(
  private val weatherRepository: WeatherRepository,
  private val geoLocationRepository: GeoLocationRepository
) : BaseViewModel() {
  var weatherFromNetwork: LiveData<ApiResponse<Weather>> = MutableLiveData()
  var weatherFromDb: LiveData<List<Weather>> = MutableLiveData()
  var searchLocation: LiveData<ApiResponse<GeoLocation>> = MutableLiveData()
  var geoLocation: MutableLiveData<GeoLocation> = MutableLiveData()
  var weather: MutableLiveData<Weather> = MutableLiveData()
  lateinit var adapter: StateAdapter

  init {
    loadFromDb()
    load(6.45, 3.39)
  }

  fun load(latitude: Double, longitude: Double) {
    weatherFromNetwork = weatherRepository.getWeathers(latitude, longitude)
  }

  fun search(search: String) {
    searchLocation = geoLocationRepository.getGeoLocation(search)
  }

  fun loadFromDb() {
    weatherFromDb = weatherRepository.getWeathersFromDb()
  }
}

