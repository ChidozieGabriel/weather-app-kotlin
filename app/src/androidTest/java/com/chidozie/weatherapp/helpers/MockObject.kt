package com.chidozie.weatherapp.helpers

import com.chidozie.weatherapp.models.*

object MockObject {

  private val weatherMain = WeatherMain(
    id = 1,
    humidity = 12.0,
    pressure = 13.0,
    temp = 12.0,
    temp_max = 25.0,
    temp_min = 4.4
  )
  private val weatherWind = WeatherWind(
    id = 1,
    deg = 12.0,
    speed = 12.5
  )
  private val weatherDescription = WeatherDescription(
    id = 1,
    main = "Clear",
    description = "clear sky",
    icon = "01n"
  )
  private val weatherDetail = WeatherDetail(
    id = 1,
    icon = "string",
    main = weatherMain,
    wind = weatherWind,
    weather = listOf(weatherDescription),
    dt = 12345678L
  )
  private val weatherCity = WeatherCity(
    id = 1,
    name = "A city"
  )
  val weather = Weather(
    id = 1,
    city = weatherCity,
    list = listOf(weatherDetail)
  )
  val weatherList = listOf(weather)
}