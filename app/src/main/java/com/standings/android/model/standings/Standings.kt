package com.standings.android.model.standings

import com.squareup.moshi.Json

data class Standings(
    @field:Json(name = "id") val id: Int,
    @field:Json(name = "name") val name: String,
    @field:Json(name = "abbreviation") val abbreviation: String,
    @field:Json(name = "logos") val logo: Link,
    @field:Json(name = "color") val color: String,
    @field:Json(name = "description") val reward: String,
    @field:Json(name = "rank") val rank: Int,
    @field:Json(name = "stats") val stats: List<Stats>,
) {
    data class Link(@field:Json(name = "href") val link: String)
}
