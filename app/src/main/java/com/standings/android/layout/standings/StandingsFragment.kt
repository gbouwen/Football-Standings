package com.standings.android.layout.standings

import android.net.Uri
import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.ImageView
import android.widget.TextView
import androidx.core.os.bundleOf
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.standings.android.R
import com.standings.android.layout.standings.season_picker.SeasonPickerDialogFragment
import com.standings.android.model.FullLeagueName
import com.standings.android.model.standings.Standings
import com.standings.android.repository.Repository
import com.standings.android.singletons.flagMap
import com.standings.android.utils.*

class StandingsFragment : Fragment(R.layout.fragment_standings) {

    companion object {
        const val RANK = "Rank"
        const val GAMES_PLAYED = "Games Played"
        const val POINTS = "Points"
    }

    private lateinit var viewModel: StandingsViewModel
    private lateinit var flag: ImageView
    private lateinit var leagueLogo: ImageView
    private lateinit var leagueName: TextView
    private lateinit var seasonPicker: View
    private lateinit var currentSeason: TextView
    private lateinit var leagueId: String
    private lateinit var recyclerView: RecyclerView
    private lateinit var errorView: TextView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        val viewModelFactory = StandingsViewModelFactory(Repository)
        viewModel = ViewModelProvider(this, viewModelFactory)[StandingsViewModel::class.java]
        leagueId = arguments?.getString("id") ?: ""
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        leagueLogo = view.findViewById(R.id.league_logo)
        flag = view.findViewById(R.id.league_country_flag)
        leagueName = view.findViewById(R.id.league_name)
        seasonPicker = view.findViewById(R.id.league_season_picker)
        currentSeason = view.findViewById(R.id.league_current_season)
        recyclerView = view.findViewById(R.id.recycler_view_standings)
        errorView = view.findViewById(R.id.error_view)

        viewModel.getLeague(leagueId)
        viewModel.getSeasons(leagueId)

        seasonPicker.visibility = View.VISIBLE
        seasonPicker.setOnClickListener {
            val dialogFragment = SeasonPickerDialogFragment()
            dialogFragment.arguments = bundleOf("leagueId" to leagueId)
            dialogFragment.show(childFragmentManager, SeasonPickerDialogFragment.TAG)
        }

        childFragmentManager.setFragmentResultListener("year", viewLifecycleOwner) { key, bundle ->
            val year = bundle.getInt(key)
            viewModel.getStandings(leagueId, year)
            currentSeason.text = setSeason(view.context, year)
        }

        viewModel.league.observe(viewLifecycleOwner) { response ->
            if (response.isSuccessful) {
                val league = response.body()!!
                val fullLeagueName = FullLeagueName(league.data.name)

                putImage(requireContext(), Uri.parse(league.data.logos.light), R.drawable.default_logo, leagueLogo)
                flag.setImageResource(flagMap[fullLeagueName.country] ?: R.drawable.default_flag)
                leagueName.text = fullLeagueName.leagueName
            } else {
                setErrorState(response.code())
            }
        }

        viewModel.allSeasons.observe(viewLifecycleOwner) { response ->
            if (response.isSuccessful) {
                val allSeasons = response.body()!!
                viewModel.getStandings(leagueId, allSeasons.data.seasons[0].year)
                currentSeason.text = setSeason(view.context, allSeasons.data.seasons[0].year)
            } else {
                setErrorState(response.code())
            }
        }

        viewModel.allStandings.observe(viewLifecycleOwner) { response ->
            if (response.isSuccessful) {
                val allStandings = response.body()!!
                setRecyclerViewAdapter(allStandings.data.standings)
            } else {
                setErrorState(response.code())
            }
        }

        recyclerView.layoutManager = LinearLayoutManager(context)
        recyclerView.addDivider(orientation = LinearLayoutManager.VERTICAL, drawableId = R.drawable.list_divider_single_horizontal)

    }

    private fun setRecyclerViewAdapter(data: List<Standings>) {
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
    }

    private fun setErrorState(errorCode: Int) {
        Log.d("RetrofitError", errorCode.toString())
        recyclerView.clear()
        errorView.visibility = View.VISIBLE
        errorView.text = requireContext().getString(R.string.error_message_http, errorCode)
    }

}