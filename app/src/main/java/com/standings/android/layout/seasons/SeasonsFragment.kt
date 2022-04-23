package com.standings.android.layout.seasons

import android.net.Uri
import android.os.Bundle
import android.view.View
import android.widget.ImageView
import android.widget.TextView
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.standings.android.R
import com.standings.android.adapters.SeasonViewAdapter
import com.standings.android.model.season.Season
import com.standings.android.repository.Repository
import com.standings.android.utils.putImage

class SeasonsFragment : Fragment(R.layout.fragment_seasons) {

    private lateinit var viewModel: SeasonsViewModel
    private lateinit var leagueId: String
    private lateinit var logo: ImageView
    private lateinit var name: TextView
    private lateinit var recyclerView: RecyclerView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        val viewModelFactory = SeasonsViewModelFactory(Repository)
        viewModel = ViewModelProvider(this, viewModelFactory)[SeasonsViewModel::class.java]
        leagueId = arguments?.getString("id") ?: ""
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        logo = view.findViewById(R.id.league_logo)
        name = view.findViewById(R.id.league_name)
        recyclerView = view.findViewById(R.id.recycler_view_seasons)

        viewModel.getLeague(leagueId)
        viewModel.getSeasons(leagueId)

        viewModel.league.observe(viewLifecycleOwner) { league ->
            putImage(requireContext(), Uri.parse(league.data.logos.light), R.drawable.default_logo, logo)
            name.text = league.data.name
        }

        viewModel.allSeasons.observe(viewLifecycleOwner) { allSeasons ->
            setRecyclerView(allSeasons.data.seasons)
        }
    }

    private fun setRecyclerView(seasons: List<Season>) {
        recyclerView.adapter = SeasonViewAdapter(seasons, R.layout.season_item) { item: Season, view: View ->
            val textView: TextView = view.findViewById(R.id.season)
            textView.text = view.context.resources.getString(R.string.season, item.year, item.year + 1)
        }
        recyclerView.layoutManager = LinearLayoutManager(context)
    }

}