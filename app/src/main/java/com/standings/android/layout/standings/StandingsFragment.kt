package com.standings.android.layout.standings

import android.net.Uri
import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.ImageView
import android.widget.TextView
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.standings.android.R
import com.standings.android.model.FullLeagueName
import com.standings.android.model.standings.Standings
import com.standings.android.repository.Repository
import com.standings.android.singletons.flagMap
import com.standings.android.utils.addDivider
import com.standings.android.utils.putImage
import com.standings.android.utils.setAdapter

class StandingsFragment : Fragment(R.layout.fragment_standings) {

    companion object {
        const val RANK = "Rank"
        const val GAMES_PLAYED = "Games Played"
        const val GOAL_DIFFERENCE = "Goal Difference"
        const val POINTS = "Points"
    }

    private lateinit var viewModel: StandingsViewModel
    private lateinit var leagueLogo: ImageView
    private lateinit var flag: ImageView
    private lateinit var leagueName: TextView
    private lateinit var season: TextView
    private lateinit var leagueId: String
    private lateinit var year: String
    private lateinit var recyclerView: RecyclerView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        val viewModelFactory = StandingsViewModelFactory(Repository)
        viewModel = ViewModelProvider(this, viewModelFactory)[StandingsViewModel::class.java]
        leagueId = arguments?.getString("id") ?: ""
        year = arguments?.getString("year") ?: ""
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        leagueLogo = view.findViewById(R.id.league_logo)
        flag = view.findViewById(R.id.league_country_flag)
        leagueName = view.findViewById(R.id.league_name)
        season = view.findViewById(R.id.league_season)
        recyclerView = view.findViewById(R.id.recycler_view_standings)

        viewModel.getLeague(leagueId)
        viewModel.getStandings(leagueId, year)

        viewModel.league.observe(viewLifecycleOwner) { league ->
            val fullLeagueName = FullLeagueName(league.data.name)

            putImage(requireContext(), Uri.parse(league.data.logos.light), R.drawable.default_logo, leagueLogo)
            flag.setImageResource(flagMap[fullLeagueName.country] ?: R.drawable.default_flag)
            leagueName.text = fullLeagueName.leagueName
            season.visibility = View.VISIBLE
            season.text = view.context.resources.getString(R.string.season, year.toInt(), year.toInt() + 1)
        }

        viewModel.allStandings.observe(viewLifecycleOwner) { allStandings ->
            setRecyclerView(allStandings.data.standings)
        }
    }

    private fun setRecyclerView(data: List<Standings>) {
        recyclerView.setAdapter(data, R.layout.standing_item) { item: Standings, view: View ->
            val rank: TextView = view.findViewById(R.id.club_rank)
            val logo: ImageView = view.findViewById(R.id.club_logo)
            val name: TextView = view.findViewById(R.id.club_name)
            val gamesPlayed: TextView = view.findViewById(R.id.club_games_played)
            val points: TextView = view.findViewById(R.id.club_points)

            rank.text = item.stats.find { it.name == RANK }?.value.toString()
            try {
                putImage(requireContext(), Uri.parse(item.team?.logos?.get(0)?.link), R.drawable.default_logo, logo)
            } catch (e: Exception) {
                logo.setImageResource(R.drawable.default_logo)
                Log.e("Logo", "Could not find team logo")
            }
            name.text = item.team?.name
            gamesPlayed.text = item.stats.find { it.name == GAMES_PLAYED }?.value.toString()
            points.text = item.stats.find { it.name == POINTS }?.value.toString()
        }
        recyclerView.layoutManager = LinearLayoutManager(context)
        recyclerView.addDivider(orientation = LinearLayoutManager.VERTICAL, drawableId = R.drawable.list_divider_single_horizontal)
    }

}