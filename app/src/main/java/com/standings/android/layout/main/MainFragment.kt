package com.standings.android.layout.main

import android.net.Uri
import android.os.Bundle
import android.view.View
import android.widget.ImageView
import android.widget.TextView
import androidx.core.os.bundleOf
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.findNavController
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.standings.android.R
import com.standings.android.model.league.LeagueData
import com.standings.android.repository.Repository
import com.standings.android.singletons.flagMap
import com.standings.android.utils.addDivider
import com.standings.android.utils.putImage
import com.standings.android.utils.setAdapter

class MainFragment : Fragment(R.layout.fragment_main) {

    private lateinit var viewModel: MainViewModel
    private lateinit var recyclerView: RecyclerView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        val viewModelFactory = MainViewModelFactory(Repository)
        viewModel = ViewModelProvider(this, viewModelFactory)[MainViewModel::class.java]
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        recyclerView = view.findViewById(R.id.recycler_view_main)

        viewModel.getLeagues()

        viewModel.leagues.observe(viewLifecycleOwner) { allLeagues ->
            setRecyclerView(allLeagues.data)
        }
    }

    private fun setRecyclerView(data: List<LeagueData>) {
        recyclerView.setAdapter(data, R.layout.league_item) { item: LeagueData, view: View ->
            val leagueLogo: ImageView = view.findViewById(R.id.league_logo)
            val flag: ImageView = view.findViewById(R.id.league_country_flag)
            val leagueName: TextView = view.findViewById(R.id.league_name)

            val splitLeagueName = item.name.split(" ")
            val country = splitLeagueName[0]
            val leagueNameWithoutCountry = item.name.replace(country, "")
            putImage(requireContext(), Uri.parse(item.logos.light), R.drawable.default_logo, leagueLogo)
            flag.setImageResource(flagMap[country] ?: R.drawable.default_flag)
            leagueName.text = leagueNameWithoutCountry
            view.setOnClickListener {
                val bundle = bundleOf("id" to item.id)
                view.findNavController().navigate(R.id.action_nav_main_to_nav_seasons, bundle)
            }
        }
        recyclerView.layoutManager = LinearLayoutManager(context)
        recyclerView.addDivider(orientation = DividerItemDecoration.VERTICAL, drawableId = R.drawable.list_divider_single_horizontal)
    }

}