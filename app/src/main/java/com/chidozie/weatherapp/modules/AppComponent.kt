package com.chidozie.weatherapp.modules

import android.app.Application
import com.chidozie.weatherapp.MyApplication
import dagger.BindsInstance
import dagger.Component
import dagger.android.support.AndroidSupportInjectionModule
import javax.inject.Named
import javax.inject.Singleton

@Singleton
@Component(
    modules = [
      (AndroidSupportInjectionModule::class),
      (ActivityModule::class),
      (ViewModelModule::class),
      (AppModule::class),
      (NetworkModule::class)
    ]
)
interface AppComponent {

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

    fun build(): AppComponent
  }

  fun inject(instance: MyApplication)
}