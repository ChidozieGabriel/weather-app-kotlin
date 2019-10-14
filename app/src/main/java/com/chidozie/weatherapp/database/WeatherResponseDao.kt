package com.chidozie.weatherResponseapp.database

import androidx.lifecycle.LiveData
import androidx.room.*
import com.chidozie.weatherapp.models.WeatherResponse

@Dao
interface WeatherResponseDao {

  // Create
  @Insert(onConflict = OnConflictStrategy.REPLACE)
  fun insertWeatherResponse(weatherResponse: WeatherResponse)

  @Insert(onConflict = OnConflictStrategy.REPLACE)
  fun insertWeatherResponses(weatherResponses: List<WeatherResponse>)

  // Read
  //    @Query("SELECT * FROM WeatherResponse WHERE date = :date")
  //    fun getWeatherResponseByDate(date: Long): LiveData<WeatherResponse>

  @Query("SELECT * FROM WeatherResponse WHERE WeatherResponse.id = :id")
  fun getWeatherResponseById(id: Int): LiveData<WeatherResponse>

  // Update
  @Update
  fun updateWeatherResponses(weatherResponses: List<WeatherResponse>)

  @Update
  fun updateWeatherResponse(weatherResponse: WeatherResponse)

  // Delete
  @Query("DELETE FROM WeatherResponse WHERE id = :id")
  fun deleteWeatherResponse(id: Int)

  @Query("DELETE FROM WeatherResponse")
  fun deleteWeatherResponses()

}