package com.standings.android.utils

import android.content.Context
import android.net.Uri
import android.widget.ImageView
import androidx.annotation.DrawableRes
import com.bumptech.glide.Glide
import com.standings.android.R

fun putImage(context: Context, uri: Uri, @DrawableRes placeholder: Int, view: ImageView) =
    Glide.with(context)
        .load(uri)
        .placeholder(placeholder)
        .into(view)

fun setSeason(context: Context, year: Int): String = context.resources.getString(R.string.season, year, year + 1)
