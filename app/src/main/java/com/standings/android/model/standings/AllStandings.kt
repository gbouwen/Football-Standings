package com.standings.android.model.standings

import com.squareup.moshi.Json

data class AllStandings(
    @field:Json(name = "status") val status: Boolean,
    @field:Json(name = "data") val data: StandingsData,
)