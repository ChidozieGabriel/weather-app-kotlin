package com.chidozie.weatherapp.modules

import com.chidozie.weatherapp.view.ui.home.HomeActivity
import com.chidozie.weatherapp.view.ui.splash.SplashActivity
import dagger.Module
import dagger.android.ContributesAndroidInjector

@Module
abstract class ActivityModule {

    @ContributesAndroidInjector
    internal abstract fun contributeHomeActivity(): HomeActivity

    @ContributesAndroidInjector
    internal abstract fun contributeSplashActivity(): SplashActivity
}