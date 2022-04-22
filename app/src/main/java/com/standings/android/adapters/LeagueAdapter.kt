package com.standings.android.adapters

import android.net.Uri
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.core.os.bundleOf
import androidx.navigation.findNavController
import androidx.recyclerview.widget.RecyclerView
import com.standings.android.R
import com.standings.android.model.league.LeagueData
import com.standings.android.utils.putImage

class LeagueAdapter(private val list: List<LeagueData>) : RecyclerView.Adapter<LeagueAdapter.ViewHolder>() {

    companion object {
        private var expandedPosition = -1
        private var previousExpandedPosition = -1
    }

    class ViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        val imageView: ImageView = view.findViewById(R.id.league_logo)
        val textView: TextView = view.findViewById(R.id.league_name)
        val button: View = view.findViewById(R.id.league_item_action)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.league_item, parent, false)
        return ViewHolder(view)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val item = list[position]

        putImage(holder.itemView.context, Uri.parse(item.logos.light), R.drawable.default_logo, holder.imageView)
        holder.textView.text = item.name
        handleExpansion(holder)
        holder.button.setOnClickListener { view ->
            val bundle = bundleOf("id" to item.id)
            view.findNavController().navigate(R.id.action_nav_main_to_nav_seasons, bundle)
        }
    }

    private fun handleExpansion(holder: ViewHolder) {
        val isExpanded = expandedPosition == holder.adapterPosition
        holder.button.visibility = if (isExpanded) View.VISIBLE else View.GONE

        if (isExpanded) {
            previousExpandedPosition = holder.adapterPosition
        }

        holder.itemView.setOnClickListener {
            expandedPosition = if (isExpanded) -1 else holder.adapterPosition
            notifyItemChanged(previousExpandedPosition)
            notifyItemChanged(holder.adapterPosition)
        }
    }

    override fun getItemCount(): Int = list.size

}