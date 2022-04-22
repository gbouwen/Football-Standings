package com.standings.android.layout.seasons

import android.net.Uri
import android.os.Bundle
import android.view.View
import android.widget.ImageView
import android.widget.TextView
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import com.standings.android.R
import com.standings.android.repository.Repository
import com.standings.android.utils.putImage

class SeasonsFragment : Fragment(R.layout.fragment_seasons) {

    private lateinit var viewModel: SeasonsViewModel
    private lateinit var leagueId: String
    private lateinit var logo: ImageView
    private lateinit var name: TextView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        val viewModelFactory = SeasonsViewModelFactory(Repository)
        viewModel = ViewModelProvider(this, viewModelFactory)[SeasonsViewModel::class.java]
        leagueId = arguments?.getString("id") ?: ""
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        logo = view.findViewById(R.id.league_logo)
        name = view.findViewById(R.id.league_name)

        viewModel.getLeague(leagueId)

        viewModel.league.observe(viewLifecycleOwner) { league ->
            putImage(requireContext(), Uri.parse(league.data.logos.light), R.drawable.default_logo, logo)
            name.text = league.data.name
        }
    }

}