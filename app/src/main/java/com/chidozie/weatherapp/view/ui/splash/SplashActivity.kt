package com.chidozie.weatherapp.view.ui.splash

import android.content.Intent
import android.os.Bundle
import android.os.Handler
import androidx.appcompat.app.AppCompatActivity
import com.chidozie.weatherapp.R
import com.chidozie.weatherapp.view.ui.home.HomeActivity
import dagger.android.AndroidInjection


class SplashActivity : AppCompatActivity() {

  //  @Inject
  //  lateinit var viewModelFactory: ViewModelFactory

  //  private val viewModel by lazy {
  //    ViewModelProviders.of(this, viewModelFactory)
  //        .get(SplashActivityViewModel::class.java)
  //  }

  override fun onCreate(savedInstanceState: Bundle?) {
    AndroidInjection.inject(this)
    super.onCreate(savedInstanceState)
    setContentView(R.layout.activity_splash)

    Handler().postDelayed({
      val intent = Intent(this, HomeActivity::class.java)
      startActivity(intent)
      finish()
    }, 3000)

  }
}