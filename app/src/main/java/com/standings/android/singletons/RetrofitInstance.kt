package com.standings.android.singletons

import com.standings.android.api.FootballStandingsApi
import com.standings.android.api.FootballStandingsApi.Companion.BASE_URL
import retrofit2.Retrofit
import retrofit2.converter.moshi.MoshiConverterFactory

object RetrofitInstance {

    private val retrofit by lazy {
        Retrofit.Builder()
            .baseUrl(BASE_URL)
            .addConverterFactory(MoshiConverterFactory.create())
            .build()
    }

    val api: FootballStandingsApi by lazy {
        retrofit.create(FootballStandingsApi::class.java)
    }

}