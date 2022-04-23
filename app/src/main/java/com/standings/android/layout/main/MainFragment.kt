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
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.standings.android.R
import com.standings.android.model.league.LeagueData
import com.standings.android.repository.Repository
import com.standings.android.utils.putImage
import com.standings.android.utils.set

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
        recyclerView.set(
            context = requireContext(),
            list = data,
            layoutId = R.layout.league_item,
            layoutManager = LinearLayoutManager(context),
            addDivider = true,
        ) { item: LeagueData, view: View ->
            val imageView: ImageView = view.findViewById(R.id.league_logo)
            val textView: TextView = view.findViewById(R.id.league_name)

            putImage(requireContext(), Uri.parse(item.logos.light), R.drawable.default_logo, imageView)
            textView.text = item.name
            view.setOnClickListener {
                val bundle = bundleOf("id" to item.id)
                view.findNavController().navigate(R.id.action_nav_main_to_nav_seasons, bundle)
            }
        }
    }

}