package com.standings.android.repository

import com.standings.android.model.league.AllLeagues
import com.standings.android.model.league.League
import com.standings.android.model.season.AllSeasons
import com.standings.android.model.standings.AllStandings
import com.standings.android.singletons.RetrofitInstance
import retrofit2.Response

object Repository {

    suspend fun getLeagues(): Response<AllLeagues> = RetrofitInstance.api.getLeagues()

    suspend fun getLeague(id: String): Response<League> = RetrofitInstance.api.getLeague(id)

    suspend fun getSeasons(id: String): Response<AllSeasons> = RetrofitInstance.api.getSeasons(id)

    suspend fun getStandings(id: String, year: Int): Response<AllStandings> = RetrofitInstance.api.getStandings(id, year)

}