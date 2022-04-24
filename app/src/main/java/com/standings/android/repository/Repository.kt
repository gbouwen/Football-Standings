package com.standings.android.repository

import com.standings.android.model.league.AllLeagues
import com.standings.android.model.league.League
import com.standings.android.model.season.AllSeasons
import com.standings.android.model.standings.AllStandings
import com.standings.android.singletons.RetrofitInstance

object Repository {

    suspend fun getLeagues(): AllLeagues = RetrofitInstance.api.getLeagues()

    suspend fun getLeague(id: String): League = RetrofitInstance.api.getLeague(id)

    suspend fun getSeasons(id: String): AllSeasons = RetrofitInstance.api.getSeasons(id)

    suspend fun getStandings(id: String, season: String): AllStandings = RetrofitInstance.api.getStandings(id, season)

}