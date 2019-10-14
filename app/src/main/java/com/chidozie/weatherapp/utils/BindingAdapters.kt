package com.chidozie.weatherapp.utils

import android.widget.ImageView
import androidx.databinding.BindingAdapter
import com.bumptech.glide.Glide
import com.bumptech.glide.request.RequestOptions

@BindingAdapter("imageUrl")
fun setImageUrl(
    imageView: ImageView,
    url: String?
) {
  val requestOptions = RequestOptions()
  requestOptions.centerCrop()

  url?.let {
    Glide.with(imageView.context)
        .load(it)
        .apply(requestOptions)
        .into(imageView)
  }
}