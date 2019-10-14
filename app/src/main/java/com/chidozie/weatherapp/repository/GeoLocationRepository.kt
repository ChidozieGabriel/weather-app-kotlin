package com.chidozie.weatherapp.repository

import androidx.lifecycle.LiveData
import com.chidozie.geoLocationapp.database.GeoLocationDao
import com.chidozie.weatherapp.api.ApiResponse
import com.chidozie.weatherapp.api.GeoLocationApi
import com.chidozie.weatherapp.models.GeoLocation
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class GeoLocationRepository @Inject constructor(
    private val geoLocationDao: GeoLocationDao,
    private val geoLocationApi: GeoLocationApi
) {

  fun getWeathersFromDb(id: Int): LiveData<List<GeoLocation>> {
    return geoLocationDao.getGeoLocations()
  }

  fun getGeoLocation(query: String): LiveData<ApiResponse<GeoLocation>> {
    return geoLocationApi.getGeoLocation(
        query = query,
        apiKey = "a10a4101958e4fca8c53fb3ec2496808"
    ) // TODO set key from build
  }
}