package com.chidozie.weatherapp.database

import androidx.room.Database
import androidx.room.RoomDatabase
import androidx.room.TypeConverters
import com.chidozie.weatherResponseapp.database.WeatherResponseDao
import com.chidozie.weatherapp.models.WeatherResponse

@Database(
    entities = [(WeatherResponse::class)],
    version = 2
)
@TypeConverters(AppTypeConverters::class)
abstract class AppDatabase : RoomDatabase() {

  abstract fun weatherDao(): WeatherResponseDao
}