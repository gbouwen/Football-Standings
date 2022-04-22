package com.standings.android.model

import com.squareup.moshi.Json

data class LeagueData(
    @field:Json(name = "id") val id: String,
    @field:Json(name = "name") val name: String,
    @field:Json(name = "slug") val slug: String,
    @field:Json(name = "abbr") val abbreviation: String,
    @field:Json(name = "logos") val logos: Logos,
)