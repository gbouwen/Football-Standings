package com.standings.android

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.View
import androidx.lifecycle.ViewModelProvider
import com.standings.android.repository.Repository

class MainActivity : AppCompatActivity() {

    private lateinit var viewModel: MainViewModel
    private lateinit var button: View

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val viewModelFactory = MainViewModelFactory(Repository())
        viewModel = ViewModelProvider(this, viewModelFactory)[MainViewModel::class.java]

        button = findViewById(R.id.main_button)

        button.setOnClickListener {
            Log.d("Main", "Calling viewModel...")
            viewModel.getLeague(id = "ned.1")
//            viewModel.getPost()
        }

//        viewModel.leagues.observe(this) { list ->
//            list.forEach { league ->
//                Log.d("League", league.id)
//                Log.d("League", league.name)
//                Log.d("League", league.slug)
//                Log.d("League", league.abbreviation)
//                Log.d("League", league.logos.light)
//                Log.d("League", league.logos.dark)
//            }
//        }

        viewModel.league.observe(this) { league ->
            Log.d("LeagueStatus", league.status.toString())
//            Log.d("League", league.name)
//            Log.d("League", league.slug)
//            Log.d("League", league.abbreviation)
//            Log.d("League", league.logos.light)
//            Log.d("League", league.logos.dark)
        }

    }

}