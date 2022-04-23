package com.standings.android.model.standings

import com.squareup.moshi.Json

data class StandingsData(
    @field:Json(name = "name") val name: String,
    @field:Json(name = "abbreviation") val abbreviation: String,
    @field:Json(name = "seasonDisplay") val season: String,
    @field:Json(name = "season") val startYear: String,
    @field:Json(name = "standings") val standings: List<Standings>,
)
