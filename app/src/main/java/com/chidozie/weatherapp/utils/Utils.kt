package com.chidozie.weatherapp.utils

object Utils {

  const val WEATHER_URL = "https://api.openweathermap.org"
  const val GEO_LOCATION_URL = "https: //api.opencagedata.com"

  fun getDay(day: Int): String {
    assert(day in 0..6)
    return when (day) {
      0 -> "MONDAY"
      1 -> "TUESDAY"
      2 -> "WEDNESDAY"
      3 -> "THURSDAY"
      4 -> "FRIDAY"
      5 -> "SATURDAY"
      else -> "SUNDAY"
    }
  }
}