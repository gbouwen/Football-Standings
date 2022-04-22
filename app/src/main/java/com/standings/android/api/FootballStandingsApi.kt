package com.standings.android.api

import com.standings.android.model.AllLeagues
import com.standings.android.model.League
import retrofit2.http.GET
import retrofit2.http.Path

interface FootballStandingsApi {

    companion object {
        const val BASE_URL = "https://api-football-standings.azharimm.site"
    }

    @GET("leagues")
    suspend fun getLeagues(): AllLeagues

    @GET("leagues/{id}")
    suspend fun getLeague(@Path("id") id: String): League

}