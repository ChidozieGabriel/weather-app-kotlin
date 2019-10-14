package com.chidozie.weatherapp

import android.app.Activity
import android.app.Application
import com.chidozie.weatherapp.modules.DaggerAppComponent
import com.chidozie.weatherapp.utils.Utils
import dagger.android.AndroidInjector
import dagger.android.DispatchingAndroidInjector
import dagger.android.HasActivityInjector
import javax.inject.Inject

class MyApplication : Application(), HasActivityInjector {

    @Inject
    lateinit var activityInjector: DispatchingAndroidInjector<Activity>

    override fun onCreate() {
        super.onCreate()

        DaggerAppComponent.builder()
            .application(this)
            .baseWeatherUrl(Utils.WEATHER_URL)
            .baseGeoLocationUrl(Utils.GEO_LOCATION_URL)
            .build()
            .inject(this)
    }

    override fun activityInjector(): AndroidInjector<Activity> = activityInjector
}