package com.chidozie.weatherapp.modules

import android.app.Application
import com.chidozie.weatherapp.view.ui.home.HomeActivityViewModelTest
import dagger.BindsInstance
import dagger.Component
import javax.inject.Named
import javax.inject.Singleton

@Singleton
@Component(
  modules = [
    (ViewModelModule::class),
    (NetworkModule::class),
    (AppModule::class)
  ]
)
interface TestAppComponent {

  @Component.Builder
  interface Builder {
    @BindsInstance
    fun application(application: Application): Builder

    @BindsInstance
    fun baseWeatherUrl(
      @Named("Weather url")
      url: String
    ): Builder

    @BindsInstance
    fun baseGeoLocationUrl(
      @Named("GeoLocation url")
      url: String
    ): Builder

    fun build(): TestAppComponent
  }

  fun inject(instance: HomeActivityViewModelTest)
}