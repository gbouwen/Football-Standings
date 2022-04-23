package com.standings.android.utils

import android.content.Context
import android.net.Uri
import android.widget.ImageView
import androidx.annotation.DrawableRes
import androidx.annotation.LayoutRes
import androidx.appcompat.content.res.AppCompatResources
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.standings.android.R
import com.standings.android.adapters.BaseRecyclerViewAdapter
import com.standings.android.adapters.RecyclerViewBindingInterface

fun putImage(context: Context, uri: Uri, @DrawableRes placeholder: Int, view: ImageView) =
    Glide.with(context)
        .load(uri)
        .placeholder(placeholder)
        .into(view)

fun <T : Any> RecyclerView.set(
    context: Context,
    list: List<T>,
    @LayoutRes layoutId: Int,
    layoutManager: RecyclerView.LayoutManager,
    addDivider: Boolean = false,
    bindData: RecyclerViewBindingInterface<T>
) {
    adapter = BaseRecyclerViewAdapter(list, layoutId, bindData)
    this.layoutManager = layoutManager
    if (addDivider) {
        val divider = DividerItemDecoration(context, DividerItemDecoration.VERTICAL)
        AppCompatResources.getDrawable(context, R.drawable.list_divider_single_horizontal)?.let { divider.setDrawable(it) }
        addItemDecoration(divider)
    }
}