package com.standings.android.layout

import android.os.Bundle
import android.view.View
import androidx.appcompat.content.res.AppCompatResources
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.standings.android.R
import com.standings.android.adapters.LeagueAdapter
import com.standings.android.model.LeagueData
import com.standings.android.repository.Repository

class MainFragment : Fragment(R.layout.fragment_main) {

    private lateinit var recyclerView: RecyclerView
    private lateinit var viewModel: MainViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        val viewModelFactory = MainViewModelFactory(Repository())
        viewModel = ViewModelProvider(this, viewModelFactory)[MainViewModel::class.java]
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        recyclerView = view.findViewById(R.id.recycler_view)

        viewModel.getLeagues()

        viewModel.leagues.observe(viewLifecycleOwner) { allLeagues ->
            setRecyclerView(allLeagues.data)
        }
    }

    private fun setRecyclerView(leagues: List<LeagueData>) {
        recyclerView.adapter = LeagueAdapter(leagues)
        recyclerView.layoutManager = LinearLayoutManager(context)
        val divider = DividerItemDecoration(context, DividerItemDecoration.VERTICAL)
        AppCompatResources.getDrawable(requireContext(), R.drawable.list_divider_single_horizontal)?.let { divider.setDrawable(it) }
        recyclerView.addItemDecoration(divider)
    }
}