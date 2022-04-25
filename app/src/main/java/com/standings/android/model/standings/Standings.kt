package com.standings.android.model.standings

import com.squareup.moshi.Json

data class Standings(
    @field:Json(name = "team") val team: Team?,
    @field:Json(name = "stats") val stats: List<Stats>,
) {
    data class Team(
        @field:Json(name = "id") val id: Int,
        @field:Json(name = "name") val name: String,
        @field:Json(name = "abbreviation") val abbreviation: String,
        @field:Json(name = "logos") val logos: List<Logo?>,
    ) {
        data class Logo(@field:Json(name = "href") val link: String?)
    }

    data class Stats(
        @field:Json(name = "displayName") val name: String,
        @field:Json(name = "abbreviation") val abbreviation: String,
        @field:Json(name = "description") val description: String,
        @field:Json(name = "value") val value: Int,
    )
}
