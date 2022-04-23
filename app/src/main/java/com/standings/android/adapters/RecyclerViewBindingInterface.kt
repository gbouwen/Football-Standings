package com.standings.android.adapters

import android.view.View

fun interface RecyclerViewBindingInterface<T> {
    fun bind(item: T, view: View)
}