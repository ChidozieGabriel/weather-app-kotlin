package com.chidozie.weatherapp.view.viewholder

import android.annotation.SuppressLint
import android.view.View
import androidx.databinding.DataBindingUtil
import androidx.recyclerview.widget.RecyclerView
import com.chidozie.weatherapp.databinding.ItemStateBinding
import com.chidozie.weatherapp.models.GeoLocationDetail


class StateViewHolder(view: View, private val clickListener: ClickListener) :
  RecyclerView.ViewHolder(view) {

  interface ClickListener {
    fun onItemClick(geoLocationDetail: GeoLocationDetail, view: View)
  }

  init {
    view.setOnClickListener { clickListener.onItemClick(geoLocationDetail, itemView) }
  }

  private lateinit var geoLocationDetail: GeoLocationDetail

  private val binding by lazy { DataBindingUtil.bind<ItemStateBinding>(view) }

  @SuppressLint("SetTextI18n")
  fun bindData(data: GeoLocationDetail) {
    geoLocationDetail = data
    val state = geoLocationDetail.components.state
    val country = geoLocationDetail.components.country.toUpperCase()
    val stateString = state?.let { "$state, " } ?: ""
    binding?.title?.text = "$stateString$country"
  }
}
