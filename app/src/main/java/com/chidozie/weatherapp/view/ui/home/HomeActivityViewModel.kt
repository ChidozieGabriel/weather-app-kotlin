package com.chidozie.weatherapp.view.ui.home

import androidx.lifecycle.MutableLiveData
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
  val geoLocation: MutableLiveData<GeoLocation> = MutableLiveData()
  val weather: MutableLiveData<Weather> = MutableLiveData()
  lateinit var adapter: StateAdapter

  fun fetchWeather(latitude: Double, longitude: Double) =
    weatherRepository.getWeathers(latitude, longitude)

  fun searchLocation(search: String) = geoLocationRepository.getGeoLocation(search)

  fun loadWeatherFromDb() = weatherRepository.getWeathersFromDb()

  fun saveWeatherToDb(weather: Weather): Unit = weatherRepository.saveToDb(weather)

}

