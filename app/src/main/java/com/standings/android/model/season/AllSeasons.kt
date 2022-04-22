package com.standings.android.model.season

import com.squareup.moshi.Json

data class AllSeasons(
    @field:Json(name = "status") val status: Boolean,
    @field:Json(name = "data") val data: SeasonData
)
