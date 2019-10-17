package com.chidozie.weatherapp.api

import androidx.lifecycle.LiveData
import com.chidozie.weatherapp.models.GeoLocation
import com.chidozie.weatherapp.utils.Utils
import retrofit2.http.GET
import retrofit2.http.Query

interface GeoLocationApi {

  @GET("/geocode/v1/json")
  fun getGeoLocation(
      @SuppressWarnings("SameParameterValue")
      @Query("q") query: String,
    @Query("key") apiKey: String = Utils.GEOLOCATION_API_KEY
  ): LiveData<ApiResponse<GeoLocation>>
}

