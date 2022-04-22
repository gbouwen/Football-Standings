package com.standings.android.adapters

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.standings.android.R
import com.standings.android.model.season.Season

class SeasonAdapter(private val list: List<Season>) : RecyclerView.Adapter<SeasonAdapter.ViewHolder>() {

    class ViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        val textView: TextView = view.findViewById(R.id.season)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.season_item, parent, false)
        return ViewHolder(view)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val item = list[position]

        holder.textView.text = holder.itemView.context.resources.getString(R.string.season, item.year, item.year + 1)
    }

    override fun getItemCount(): Int = list.size

}