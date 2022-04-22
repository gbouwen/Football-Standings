package com.standings.android.model.league

import com.squareup.moshi.Json

data class AllLeagues(
    @field:Json(name = "status") val status: Boolean,
    @field:Json(name = "data") val data: List<LeagueData>,
)