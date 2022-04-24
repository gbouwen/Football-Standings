package com.standings.android.adapters

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.annotation.LayoutRes
import androidx.recyclerview.widget.RecyclerView

class BaseRecyclerViewAdapter<T : Any>(
    private val list: List<T>,
    @LayoutRes private val layoutId: Int,
    private val bindingInterface: RecyclerViewBindingInterface<T>,
) : RecyclerView.Adapter<BaseRecyclerViewAdapter.ViewHolder>() {

    class ViewHolder(private val view: View) : RecyclerView.ViewHolder(view) {
        fun <T : Any> bind(item: T, bindingInterface: RecyclerViewBindingInterface<T>) {
            bindingInterface.bind(item, view)
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(layoutId, parent, false)
        return ViewHolder(view)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val item = list[position]
        holder.bind(item, bindingInterface)
    }

    override fun getItemCount(): Int = list.size

}