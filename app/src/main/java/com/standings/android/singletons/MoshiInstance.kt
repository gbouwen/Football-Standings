package com.standings.android.singletons

import com.squareup.moshi.Moshi

object MoshiInstance {
    val moshi = Moshi.Builder().build()
}