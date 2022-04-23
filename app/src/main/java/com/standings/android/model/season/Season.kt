package com.standings.android.model.season

import com.squareup.moshi.Json

data class Season(
    @field:Json(name = "year") val year: Int,
    @field:Json(name = "startDate") val startDate: String,
    @field:Json(name = "endDate") val endDate: String,
    @field:Json(name = "displayName") val displayName: String,
    @field:Json(name = "types") val types: List<Type>,
) {
    data class Type(
        @field:Json(name = "id") val id: Int,
        @field:Json(name = "name") val name: String,
        @field:Json(name = "abbreviation") val abbreviation: String,
        @field:Json(name = "startDate") val startDate: String,
        @field:Json(name = "endDate") val endDate: String,
        @field:Json(name = "hasStandings") val hasStandings: Boolean,
    )
}
