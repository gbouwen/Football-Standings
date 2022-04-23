package com.standings.android.utils

import android.view.View

fun interface RecyclerViewBindingInterface<T> {
    fun bind(item: T, view: View)
}