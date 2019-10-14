package com.chidozie.weatherapp.view

import android.widget.ImageView
import com.bumptech.glide.Glide
import com.bumptech.glide.request.RequestOptions


fun ImageView.loadImage(photoUrl: String, centerCropped: Boolean = false) {
  val requestOptions = RequestOptions()
  Glide.with(context)
    .load(photoUrl)
    .apply { if (centerCropped) requestOptions.centerCrop() }
    .into(this)
}

fun Double.FromKelvinToCelsius(): Double = this - 273.15

fun Double.toFixed1(): Double = String.format("%.1f", this).toDouble()