package com.standings.android.api

import com.standings.android.model.League
import com.standings.android.model.Post
import retrofit2.http.GET
import retrofit2.http.Path

interface FootballStandingsApi {

    companion object {
        //        const val BASE_URL = "https://api-football-standings.azharimm.site"
        const val BASE_URL = "https://jsonplaceholder.typicode.com"
    }

//    @GET("leagues")
//    suspend fun getLeagues(): List<League>
//
//    @GET("leagues/{id}")
//    suspend fun getLeague(@Path("id") id: String): League

    @GET("posts/1")
    suspend fun getPost(): Post
}