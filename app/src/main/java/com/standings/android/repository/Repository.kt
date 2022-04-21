package com.standings.android.repository

import com.standings.android.api.RetrofitInstance
import com.standings.android.model.League

class Repository {

//    suspend fun getLeagues(): List<League> = RetrofitInstance.api.getLeagues()

//    suspend fun getLeague(id: String) = RetrofitInstance.api.getLeague(id)

    suspend fun getPost() = RetrofitInstance.api.getPost()

}