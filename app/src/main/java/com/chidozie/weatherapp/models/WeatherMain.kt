package com.chidozie.weatherapp.models

import android.os.Parcelable
import com.chidozie.weatherapp.view.fromKelvinToCelsius
import com.chidozie.weatherapp.view.toFixed1
import kotlinx.android.parcel.Parcelize

@Parcelize
data class WeatherMain(
  val id: Int,
  val temp: Double,
  val pressure: Double,
  val humidity: Double,
  val temp_min: Double,
  val temp_max: Double
) : Parcelable {

  fun getTemp() = "${temp.fromKelvinToCelsius().toFixed1()} C"

  fun getTempMax() = "max: ${temp_max.fromKelvinToCelsius().toFixed1()} C"

  fun getTempMin() = "min: ${temp_min.fromKelvinToCelsius().toFixed1()} C"
}
