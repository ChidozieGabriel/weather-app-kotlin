package com.chidozie.weatherapp.api

import androidx.lifecycle.LiveData
import com.chidozie.weatherapp.models.Weather
import retrofit2.http.GET
import retrofit2.http.Query

interface WeatherApi {

  @GET("/data/2.5/forecast")
  fun getWeather(
      @SuppressWarnings("SameParameterValue")
      @Query("lon") longitude: Double,
      @Query("lat") latitude: Double,
      @Query("APPID") apiKey: String,
      @Query("cnt") limit: Int = 6
  ): LiveData<ApiResponse<Weather>>
}
