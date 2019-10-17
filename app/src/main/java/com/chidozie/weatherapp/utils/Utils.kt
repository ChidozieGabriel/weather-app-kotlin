package com.chidozie.weatherapp.utils

object Utils {

  const val WEATHER_URL = "https://api.openweathermap.org"
  const val GEO_LOCATION_URL = "https://api.opencagedata.com"
  const val WEATHER_API_KEY = "bbeb34ebf60ad50f7893e7440a1e2b0b"
  const val GEOLOCATION_API_KEY = "a10a4101958e4fca8c53fb3ec2496808"

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