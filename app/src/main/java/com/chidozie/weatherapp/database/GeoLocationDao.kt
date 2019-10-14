package com.chidozie.geoLocationapp.database

import androidx.lifecycle.LiveData
import androidx.room.*
import com.chidozie.weatherapp.models.GeoLocation

@Dao
interface GeoLocationDao {

  // Create
  @Insert(onConflict = OnConflictStrategy.REPLACE)
  fun insertGeoLocation(geoLocation: GeoLocation)

  @Insert(onConflict = OnConflictStrategy.REPLACE)
  fun insertGeoLocations(geoLocations: List<GeoLocation>)

  // Read
  //    @Query("SELECT * FROM GeoLocation WHERE date = :date")
  //    fun getGeoLocationByDate(date: Long): LiveData<GeoLocation>

  @Query("SELECT * FROM GeoLocation WHERE GeoLocation.id = :id")
  fun getGeoLocationById(id: Int): LiveData<GeoLocation>


  @Query("SELECT * FROM GeoLocation")
  fun getGeoLocations(): LiveData<List<GeoLocation>>

  // Update
  @Update
  fun updateGeoLocations(geoLocations: List<GeoLocation>)

  @Update
  fun updateGeoLocation(geoLocation: GeoLocation)

  // Delete
  @Query("DELETE FROM GeoLocation WHERE id = :id")
  fun deleteGeoLocation(id: Int)

  @Query("DELETE FROM GeoLocation")
  fun deleteGeoLocations()

}