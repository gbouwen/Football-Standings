package com.standings.android.adapters

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.standings.android.R
import com.standings.android.model.LeagueData

class LeagueAdapter(private val list: List<LeagueData>) : RecyclerView.Adapter<LeagueAdapter.ViewHolder>() {

    class ViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        val imageView: ImageView = view.findViewById(R.id.league_logo)
        val textView: TextView = view.findViewById(R.id.league_name)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.league_item, parent, false)
        return ViewHolder(view)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val item = list[position]

//        holder.imageView TODO set imageView to logo
        holder.textView.text = item.name
    }

    override fun getItemCount(): Int = list.size

}