package com.chidozie.weatherapp.modules

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.chidozie.weatherapp.factory.ViewModelFactory
import com.chidozie.weatherapp.view.ui.home.HomeActivityViewModel
import com.chidozie.weatherapp.view.ui.splash.SplashActivityViewModel
import dagger.Binds
import dagger.MapKey
import dagger.Module
import dagger.multibindings.IntoMap
import kotlin.reflect.KClass

@Module
internal abstract class ViewModelModule {

    @Binds
    @IntoMap
    @ViewModelKey(HomeActivityViewModel::class)
    internal abstract fun bindHomeActivityViewModel(homeActivityViewModel: HomeActivityViewModel): ViewModel

    @Binds
    @IntoMap
    @ViewModelKey(SplashActivityViewModel::class)
    internal abstract fun bindSplashActivityViewModel(splashActivityViewModel: SplashActivityViewModel): ViewModel

    @Binds
    internal abstract fun bindViewModelFactory(viewModelFactory: ViewModelFactory): ViewModelProvider.Factory
}

@kotlin.annotation.Retention
@Target(
    AnnotationTarget.FUNCTION, AnnotationTarget.PROPERTY_GETTER, AnnotationTarget.PROPERTY_SETTER
)
@MapKey
internal annotation class ViewModelKey(val value: KClass<out ViewModel>)