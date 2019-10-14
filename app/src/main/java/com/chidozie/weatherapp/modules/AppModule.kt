package com.chidozie.weatherapp.modules

import android.app.Application
import androidx.room.Room
import com.chidozie.weatherResponseapp.database.WeatherResponseDao
import com.chidozie.weatherapp.database.AppDatabase
import dagger.Module
import dagger.Provides
import javax.inject.Singleton

@Module
class AppModule {

    @Provides
    @Singleton
    fun provideDatabase(application: Application): AppDatabase {
        return Room.databaseBuilder(
            application.applicationContext, AppDatabase::class.java, "weatherDB" + ".db"
        )
            .allowMainThreadQueries()
            .fallbackToDestructiveMigration()
            .build()
    }

    @Provides
    @Singleton
    fun provideWeatherDao(database: AppDatabase): WeatherResponseDao {
        return database.weatherDao()
    }


}