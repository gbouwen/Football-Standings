package com.standings.android

import android.os.Bundle
import android.util.Log
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.ViewModelProvider
import com.standings.android.repository.Repository

class MainActivity : AppCompatActivity() {

    private lateinit var viewModel: MainViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val viewModelFactory = MainViewModelFactory(Repository())
        viewModel = ViewModelProvider(this, viewModelFactory)[MainViewModel::class.java]

        viewModel.getLeagues()

        viewModel.leagues.observe(this) { list ->
            list.data.forEach { league ->
                Log.d("League", league.id)
                Log.d("League", league.name)
                Log.d("League", league.slug)
                Log.d("League", league.abbreviation)
                Log.d("League", league.logos.light)
                Log.d("League", league.logos.dark)
            }
        }
    }

}