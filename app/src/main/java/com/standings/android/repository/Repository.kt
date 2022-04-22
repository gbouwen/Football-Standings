package com.standings.android.repository

import com.standings.android.api.RetrofitInstance
import com.standings.android.model.AllLeagues
import com.standings.android.model.League

class Repository {

    suspend fun getLeagues(): AllLeagues = RetrofitInstance.api.getLeagues()

    suspend fun getLeague(id: String): League = RetrofitInstance.api.getLeague(id)

}