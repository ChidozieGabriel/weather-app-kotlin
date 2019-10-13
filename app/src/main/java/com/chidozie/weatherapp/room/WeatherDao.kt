package com.chidozie.weatherapp.room

import androidx.lifecycle.LiveData
import androidx.room.*
import com.chidozie.weatherapp.models.Weather

@Dao
interface WeatherDao {

    // Create
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertWeather(weather: Weather)

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertWeathers(weathers: List<Weather>)

    // Read
    @Query("SELECT * FROM Weather WHERE date = :date")
    fun getWeatherByDate(date: Long): LiveData<Weather>

    @Query("SELECT * FROM Weather WHERE Weather.id = :id")
    fun getWeatherById(id: Int): LiveData<Weather>

    // Update
    @Update
    fun updateWeathers(weathers: List<Weather>)

    @Update
    fun updateWeather(weather: Weather)

    // Delete
    @Query("DELETE FROM Weather WHERE id = :id")
    fun deleteWeather(id: Int)

    @Query("DELETE FROM Weather")
    fun deleteWeathers()

}