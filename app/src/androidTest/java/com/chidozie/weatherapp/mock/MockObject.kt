package com.chidozie.weatherapp.mock

import com.chidozie.weatherapp.models.WeatherDescription
import com.chidozie.weatherapp.models.WeatherDetail
import com.chidozie.weatherapp.models.WeatherMain
import com.chidozie.weatherapp.models.WeatherWind

object MockObject {

  val weatherMain = WeatherMain(
      id = 1,
      humidity = 12.0,
      pressure = 13.0,
      temp = 12.0,
      temp_max = 25.0,
      temp_min = 4.4
  )
  val weatherWind = WeatherWind(
      id = 1,
      deg = 12.0,
      speed = 12.5
  )
  val weatherDetail = WeatherDescription(
      id = 1,
      main = "Clear",
      description = "clear sky",
      icon = "01n"
  )
  val exampleWeather = WeatherDetail(
      id = 1,
      icon = "string",
      main = weatherMain,
      wind = weatherWind,
      weather = listOf(weatherDetail)
  )
  val exampleWeatherList = listOf(exampleWeather)
}