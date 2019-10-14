package com.chidozie.weatherapp.database

import androidx.room.TypeConverter
import com.chidozie.weatherapp.models.GeoLocationDetail
import com.chidozie.weatherapp.models.WeatherDescription
import com.chidozie.weatherapp.models.WeatherDetail
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken
import java.util.*

class AppTypeConverters {
  private val gson = Gson()

  @TypeConverter
  fun stringToListWeatherDetail(data: String?): List<WeatherDescription> {
    if (data == null) {
      return Collections.emptyList()
    }
    val listType = object : TypeToken<List<WeatherDescription>>() {}.type
    return gson.fromJson(data, listType)
  }

  @TypeConverter
  fun weatherDetailListToString(list: List<WeatherDescription>?): String? =
      list?.let { gson.toJson(list) }

  @TypeConverter
  fun stringToListWeather(data: String?): List<WeatherDetail> {
    if (data == null) {
      return Collections.emptyList()
    }
    val listType = object : TypeToken<List<WeatherDetail>>() {}.type
    return gson.fromJson(data, listType)
  }

  @TypeConverter
  fun weatherListToString(list: List<WeatherDetail>?): String? =
      list?.let { gson.toJson(list) }

  @TypeConverter
  fun stringToListgeoLocationDetail(data: String?): List<GeoLocationDetail> {
    if (data == null) {
      return Collections.emptyList()
    }
    val listType = object : TypeToken<List<GeoLocationDetail>>() {}.type
    return gson.fromJson(data, listType)
  }

  @TypeConverter
  fun geoLocationDetailListToString(list: List<GeoLocationDetail>?): String? =
      list?.let { gson.toJson(list) }

}