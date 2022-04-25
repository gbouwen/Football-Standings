package com.standings.android.api

import com.standings.android.model.league.AllLeagues
import com.standings.android.model.league.League
import com.standings.android.model.season.AllSeasons
import com.standings.android.model.standings.AllStandings
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

interface FootballStandingsApi {

    companion object {
        const val BASE_URL = "https://api-football-standings.azharimm.site"
        const val LEAGUES = "leagues"
        const val ID = "id"
    }

    @GET(LEAGUES)
    suspend fun getLeagues(): AllLeagues

    @GET("$LEAGUES/{$ID}")
    suspend fun getLeague(@Path("id") id: String): League

    @GET("$LEAGUES/{$ID}/seasons")
    suspend fun getSeasons(@Path("id") id: String): AllSeasons

    @GET("$LEAGUES/{$ID}/standings")
    suspend fun getStandings(@Path("id") id: String, @Query("season") season: Int): AllStandings

}