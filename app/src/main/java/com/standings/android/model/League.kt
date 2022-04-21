package com.standings.android.model

import com.squareup.moshi.Json

data class League(
    @field:Json(name = "id") val id: String,
    @field:Json(name = "name") val name: String,
    @field:Json(name = "slug") val slug: String,
    @field:Json(name = "abbr") val abbreviation: String,
//    @field:Json(name = "") val logos: Logos,
)