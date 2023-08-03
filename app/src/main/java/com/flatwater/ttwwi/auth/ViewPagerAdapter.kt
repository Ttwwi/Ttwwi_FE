package com.flatwater.ttwwi.auth

import android.view.LayoutInflater
import android.view.ViewGroup
import android.widget.ImageView
import androidx.recyclerview.widget.RecyclerView
import com.flatwater.ttwwi.R

class ViewPagerAdapter(var illustrationList: ArrayList<Int>) : RecyclerView.Adapter<ViewPagerAdapter.PagerViewHolder>() {
    val item = illustrationList

    inner class PagerViewHolder(parent: ViewGroup) : RecyclerView.ViewHolder (LayoutInflater.from(parent.context).inflate(R.layout.illustrations_list_item, parent, false)) {
        val illustration = itemView.findViewById<ImageView>(R.id.illustration)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int) = PagerViewHolder((parent))

    override fun onBindViewHolder(holder: PagerViewHolder, position: Int) {
        holder.illustration.setImageResource(item[position])
    }

    override fun getItemCount(): Int = illustrationList.size
}