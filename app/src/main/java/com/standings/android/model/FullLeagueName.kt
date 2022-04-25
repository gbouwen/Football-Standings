package com.standings.android.model

data class FullLeagueName(val string: String) {
    private val splitLeagueName = string.split(" ")
    val country = splitLeagueName[0]
    val leagueName = string.replace(country, "")
}
