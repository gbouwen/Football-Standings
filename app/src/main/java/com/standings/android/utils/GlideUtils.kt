package com.standings.android.utils

import android.content.Context
import android.net.Uri
import android.widget.ImageView
import androidx.annotation.DrawableRes
import com.bumptech.glide.Glide

fun putImage(context: Context, uri: Uri, @DrawableRes placeholder: Int, view: ImageView) =
    Glide.with(context)
        .load(uri)
        .placeholder(placeholder)
        .into(view)