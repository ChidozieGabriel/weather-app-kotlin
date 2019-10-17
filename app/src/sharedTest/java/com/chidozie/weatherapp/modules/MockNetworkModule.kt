package com.chidozie.weatherapp.modules

import com.chidozie.weatherapp.api.GeoLocationApi
import com.chidozie.weatherapp.api.LiveDataCallAdapterFactory
import com.chidozie.weatherapp.api.WeatherApi
import dagger.Module
import dagger.Provides
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.util.concurrent.TimeUnit
import javax.inject.Named
import javax.inject.Singleton

@Module
class MockNetworkModule {

  @Provides
  @Singleton
  internal fun provideInterceptor(): HttpLoggingInterceptor =
    HttpLoggingInterceptor().apply { level = HttpLoggingInterceptor.Level.BODY }

  @Provides
  @Singleton
  fun providesOkHttpClient(httpLoggingInterceptor: HttpLoggingInterceptor): OkHttpClient {
    return OkHttpClient.Builder()
      .addInterceptor(httpLoggingInterceptor)
      .connectTimeout(1, TimeUnit.MINUTES)
      .writeTimeout(1, TimeUnit.MINUTES)
      .readTimeout(2, TimeUnit.MINUTES)
      .build()
  }

  @Provides
  @Named("Weather_Retrofit")
  @Singleton
  fun provideWeatherRetrofit(
    okHttpClient: OkHttpClient,
    @Named("Weather url")
    url: String
  ): Retrofit {
    return Retrofit.Builder()
      .baseUrl(url)
      .client(okHttpClient)
      .addConverterFactory(GsonConverterFactory.create())
      .addCallAdapterFactory(LiveDataCallAdapterFactory())
      .build()
  }

  @Provides
  @Named("GeoLocation_Retrofit")
  @Singleton
  fun provideGeoLocationRetrofit(
    okHttpClient: OkHttpClient,
    @Named("GeoLocation url")
    url: String
  ): Retrofit {
    return Retrofit.Builder()
      .baseUrl(url)
      .client(okHttpClient)
      .addConverterFactory(GsonConverterFactory.create())
      .addCallAdapterFactory(LiveDataCallAdapterFactory())
      .build()
  }

  @Provides
  @Singleton
  fun provideWeatherApiService(
    @Named("Weather_Retrofit")
    retrofit: Retrofit
  ): WeatherApi =
    retrofit.create(WeatherApi::class.java)

  @Provides
  @Singleton
  fun provideGeoLocationApiService(
    @Named("GeoLocation_Retrofit")
    retrofit: Retrofit
  ): GeoLocationApi =
    retrofit.create(GeoLocationApi::class.java)
}