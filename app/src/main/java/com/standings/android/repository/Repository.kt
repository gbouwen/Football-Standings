package com.standings.android.repository

import com.standings.android.api.RetrofitInstance
import com.standings.android.model.AllLeagues

class Repository {

    suspend fun getLeagues(): AllLeagues = RetrofitInstance.api.getLeagues()

}