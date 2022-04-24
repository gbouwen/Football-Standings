package com.standings.android.layout.seasons

import android.net.Uri
import android.os.Bundle
import android.view.View
import android.widget.Button
import android.widget.ImageView
import android.widget.TextView
import androidx.core.os.bundleOf
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.standings.android.R
import com.standings.android.model.season.Season
import com.standings.android.repository.Repository
import com.standings.android.singletons.flagMap
import com.standings.android.utils.putImage
import com.standings.android.utils.setAdapter

class SeasonsFragment : Fragment(R.layout.fragment_seasons) {

    private lateinit var viewModel: SeasonsViewModel
    private lateinit var leagueId: String
    private lateinit var leagueLogo: ImageView
    private lateinit var flag: ImageView
    private lateinit var leagueName: TextView
    private lateinit var recyclerView: RecyclerView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        val viewModelFactory = SeasonsViewModelFactory(Repository)
        viewModel = ViewModelProvider(this, viewModelFactory)[SeasonsViewModel::class.java]
        leagueId = arguments?.getString("id") ?: ""
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        leagueLogo = view.findViewById(R.id.league_logo)
        flag = view.findViewById(R.id.league_country_flag)
        leagueName = view.findViewById(R.id.league_name)
        recyclerView = view.findViewById(R.id.recycler_view_seasons)

        viewModel.getLeague(leagueId)
        viewModel.getSeasons(leagueId)

        viewModel.league.observe(viewLifecycleOwner) { league ->
            val splitLeagueName = league.data.name.split(" ")
            val country = splitLeagueName[0]
            val leagueNameWithoutCountry = league.data.name.replace(country, "")

            putImage(requireContext(), Uri.parse(league.data.logos.light), R.drawable.default_logo, leagueLogo)
            flag.setImageResource(flagMap[country] ?: R.drawable.default_flag)
            leagueName.text = leagueNameWithoutCountry
        }

        viewModel.allSeasons.observe(viewLifecycleOwner) { allSeasons ->
            setRecyclerView(allSeasons.data.seasons)
        }
    }

    private fun setRecyclerView(data: List<Season>) {
        recyclerView.setAdapter(data, R.layout.season_item) { item: Season, view: View ->
            val button: Button = view.findViewById(R.id.season)
            button.text = view.context.resources.getString(R.string.season, item.year, item.year + 1)
            button.setOnClickListener {
                val bundle = bundleOf("id" to leagueId, "year" to item.year.toString())
                findNavController().navigate(R.id.action_nav_seasons_to_nav_standings, bundle)
            }
        }
        recyclerView.layoutManager = LinearLayoutManager(context)
    }

}