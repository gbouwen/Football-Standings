package com.standings.android.model.season

import com.squareup.moshi.Json

data class SeasonData(
    @field:Json(name = "desc") val description: String,
    @field:Json(name = "seasons") val seasons: List<Season>
)
