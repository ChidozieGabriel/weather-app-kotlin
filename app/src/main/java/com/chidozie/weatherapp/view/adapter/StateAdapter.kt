package com.chidozie.weatherapp.view.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.chidozie.weatherapp.R
import com.chidozie.weatherapp.models.GeoLocationDetail
import com.chidozie.weatherapp.view.viewholder.StateViewHolder

class StateAdapter(private val clickListener: StateViewHolder.ClickListener) :
  RecyclerView.Adapter<StateViewHolder>() {
  private var items: List<GeoLocationDetail> = listOf()

  fun updateItems(items: List<GeoLocationDetail>) {
    this.items = items
    notifyDataSetChanged()
  }

  override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): StateViewHolder {
    val itemView = LayoutInflater.from(parent.context).inflate(R.layout.item_state, parent, false)
    return StateViewHolder(itemView, clickListener)
  }

  override fun getItemCount(): Int {
    return items.size
  }

  override fun onBindViewHolder(holder: StateViewHolder, position: Int) {
    val data = items[position]
    holder.bindData(data)
  }


}
