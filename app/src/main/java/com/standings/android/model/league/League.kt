package com.standings.android.model.league

import com.squareup.moshi.Json

data class League(
    @field:Json(name = "status") val status: Boolean,
    @field:Json(name = "data") val data: LeagueData,
)