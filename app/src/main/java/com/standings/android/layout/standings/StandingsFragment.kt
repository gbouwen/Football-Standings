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
import com.standings.android.model.standings.Standings
import com.standings.android.repository.Repository
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
        recyclerView = view.findViewById(R.id.recycler_view_standings)

        viewModel.getStandings(leagueId, year)

        viewModel.allStandings.observe(viewLifecycleOwner) { allStandings ->
            Log.d("AllStandings", allStandings.toString())
            setRecyclerView(allStandings.data.standings)
        }
    }

    private fun setRecyclerView(data: List<Standings>) {
        recyclerView.setAdapter(data, R.layout.standing_item) { item: Standings, view: View ->
            val rank: TextView = view.findViewById(R.id.club_rank)
            val logo: ImageView = view.findViewById(R.id.club_logo)
            val name: TextView = view.findViewById(R.id.club_name)
            val gamesPlayed: TextView = view.findViewById(R.id.club_games_played)
            val goalDifference: TextView = view.findViewById(R.id.club_goal_difference)
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
            goalDifference.text = item.stats.find { it.name == GOAL_DIFFERENCE }?.value.toString()
            points.text = item.stats.find { it.name == POINTS }?.value.toString()
        }
        recyclerView.layoutManager = LinearLayoutManager(context)
        recyclerView.addDivider(orientation = LinearLayoutManager.VERTICAL, drawableId = R.drawable.list_divider_single_horizontal)
    }

}