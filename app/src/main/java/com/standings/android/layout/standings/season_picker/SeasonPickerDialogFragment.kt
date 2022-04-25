package com.standings.android.layout.standings.season_picker

import android.os.Bundle
import android.view.View
import android.widget.TextView
import androidx.fragment.app.DialogFragment
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.standings.android.R
import com.standings.android.model.season.Season
import com.standings.android.repository.Repository
import com.standings.android.utils.addDivider
import com.standings.android.utils.setAdapter

class SeasonPickerDialogFragment(
    private val leagueId: String,
    private val seasonListenerInterface: SeasonListenerInterface,
) : DialogFragment(R.layout.fragment_season_picker) {

    private lateinit var viewModel: SeasonPickerViewModel
    private lateinit var recyclerView: RecyclerView

    companion object {
        const val TAG = "SeasonPickerDialogFragment"
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        val viewModelFactory = SeasonPickerViewModelFactory(Repository)
        viewModel = ViewModelProvider(this, viewModelFactory)[SeasonPickerViewModel::class.java]
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        recyclerView = view.findViewById(R.id.recycler_view_season_picker)

        viewModel.getAllSeasons(leagueId)

        viewModel.allSeasons.observe(viewLifecycleOwner) { allSeasons ->
            setRecyclerView(allSeasons.data.seasons)
        }
    }

    private fun setRecyclerView(data: List<Season>) {
        recyclerView.setAdapter(data, R.layout.season_item) { item: Season, view: View ->
            val season: TextView = view.findViewById(R.id.season_item_text)

            season.text = view.resources.getString(R.string.season, item.year, item.year + 1)
            view.setOnClickListener {
                seasonListenerInterface.listen(item.year)
                dismiss()
            }
        }
        recyclerView.layoutManager = LinearLayoutManager(context)
        recyclerView.addDivider(orientation = LinearLayoutManager.VERTICAL, drawableId = R.drawable.list_divider_single_horizontal)
    }

}