package com.standings.android.utils

import androidx.annotation.DrawableRes
import androidx.annotation.LayoutRes
import androidx.appcompat.content.res.AppCompatResources
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.RecyclerView
import com.standings.android.adapters.BaseRecyclerViewAdapter
import com.standings.android.adapters.RecyclerViewBindingInterface

fun <T : Any> RecyclerView.setAdapter(
    data: List<T>,
    @LayoutRes layoutId: Int,
    bindData: RecyclerViewBindingInterface<T>,
) {
    adapter = BaseRecyclerViewAdapter(data, layoutId, bindData)
}

fun RecyclerView.addDivider(orientation: Int, @DrawableRes drawableId: Int) {
    val divider = DividerItemDecoration(context, orientation)
    AppCompatResources.getDrawable(context, drawableId)?.let { divider.setDrawable(it) }
    addItemDecoration(divider)
}
