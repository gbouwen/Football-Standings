package com.standings.android.api

import com.standings.android.model.AllLeagues
import retrofit2.http.GET

interface FootballStandingsApi {

    companion object {
        const val BASE_URL = "https://api-football-standings.azharimm.site"
    }

    @GET("leagues")
    suspend fun getLeagues(): AllLeagues

}