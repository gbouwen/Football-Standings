package com.standings.android.model.standings

import com.squareup.moshi.Json

data class Stats(
    @field:Json(name = "displayName") val name: String,
    @field:Json(name = "abbreviation") val abbreviation: String,
    @field:Json(name = "description") val description: String,
    @field:Json(name = "value") val value: Int,
)
