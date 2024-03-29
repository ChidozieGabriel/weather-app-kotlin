package com.chidozie.weatherapp.repository

import androidx.lifecycle.LiveData
import com.chidozie.weatherapp.api.ApiResponse
import com.chidozie.weatherapp.api.WeatherApi
import com.chidozie.weatherapp.database.WeatherDao
import com.chidozie.weatherapp.models.Weather
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class WeatherRepository @Inject constructor(
    private val weatherDao: WeatherDao,
    private val weatherApi: WeatherApi
) {

  fun getWeathersFromDb(): LiveData<List<Weather>> {
    return weatherDao.getWeathers()
  }

  fun saveToDb(weather: Weather) {
    weatherDao.insertWeather(weather)
  }

  fun getWeathers(
      latitude: Double,
      longitude: Double
  ): LiveData<ApiResponse<Weather>> {
    return weatherApi.getWeather(
        latitude = latitude,
      longitude = longitude
    ) // TODO set key from build
  }
}