package com.chidozie.weatherapp.database

import androidx.room.TypeConverter
import com.chidozie.weatherapp.models.WeatherDetail
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken
import java.util.*

class AppTypeConverters {
  private val gson = Gson()

  @TypeConverter
  fun stringToListWeatherDetail(data: String?): List<WeatherDetail> {
    if (data == null) {
      return Collections.emptyList()
    }
    val listType = object : TypeToken<List<WeatherDetail>>() {}.type
    return gson.fromJson(data, listType)
  }

  @TypeConverter
  fun weatherDetailListToString(list: List<WeatherDetail>?): String? =
      list?.let { gson.toJson(list) }

}