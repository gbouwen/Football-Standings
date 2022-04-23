package com.standings.android.repository

import com.standings.android.singletons.RetrofitInstance
import com.standings.android.model.league.AllLeagues
import com.standings.android.model.league.League
import com.standings.android.model.season.AllSeasons

object Repository {

    suspend fun getLeagues(): AllLeagues = RetrofitInstance.api.getLeagues()

    suspend fun getLeague(id: String): League = RetrofitInstance.api.getLeague(id)

    suspend fun getSeasons(id: String): AllSeasons = RetrofitInstance.api.getSeasons(id)

}