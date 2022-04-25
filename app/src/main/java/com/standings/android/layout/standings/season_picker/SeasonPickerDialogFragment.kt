package com.standings.android.layout.standings.season_picker

import android.os.Bundle
import android.view.View
import android.widget.TextView
import androidx.core.os.bundleOf
import androidx.fragment.app.DialogFragment
import androidx.fragment.app.setFragmentResult
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.standings.android.R
import com.standings.android.repository.Repository
import com.standings.android.utils.addDivider
import com.standings.android.utils.setAdapter

class SeasonPickerDialogFragment() : DialogFragment(R.layout.fragment_season_picker) {

    private lateinit var viewModel: SeasonPickerViewModel
    private lateinit var recyclerView: RecyclerView
    private lateinit var leagueId: String

    companion object {
        const val TAG = "SeasonPickerDialogFragment"
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        val viewModelFactory = SeasonPickerViewModelFactory(Repository)
        viewModel = ViewModelProvider(this, viewModelFactory)[SeasonPickerViewModel::class.java]
        leagueId = arguments?.getString("leagueId") ?: ""
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        recyclerView = view.findViewById(R.id.recycler_view_season_picker)

        viewModel.getYears(leagueId)

        viewModel.years.observe(viewLifecycleOwner) { years ->
            setRecyclerViewAdapter(years)
        }
        recyclerView.layoutManager = LinearLayoutManager(context)
        recyclerView.addDivider(orientation = LinearLayoutManager.VERTICAL, drawableId = R.drawable.list_divider_single_horizontal)
    }

    private fun setRecyclerViewAdapter(data: List<Int>) {
        recyclerView.setAdapter(data, R.layout.season_item) { year: Int, view: View ->
            val season: TextView = view.findViewById(R.id.season_item_text)

            season.text = view.resources.getString(R.string.season, year, year + 1)
            view.setOnClickListener {
                setFragmentResult("year", bundleOf("year" to year))
                dismiss()
            }
        }
    }

}