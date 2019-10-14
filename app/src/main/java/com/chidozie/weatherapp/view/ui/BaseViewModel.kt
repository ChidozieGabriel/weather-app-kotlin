package com.chidozie.weatherapp.view.ui

import androidx.lifecycle.ViewModel
import io.reactivex.disposables.CompositeDisposable

abstract class BaseViewModel : ViewModel() {

  protected var subscriptions = CompositeDisposable()

  fun onViewDestroyed() {
    subscriptions.dispose()
  }
}