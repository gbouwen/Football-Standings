package com.standings.android.layout.standings.season_picker

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.standings.android.model.season.AllSeasons
import com.standings.android.repository.Repository
import kotlinx.coroutines.launch

class SeasonPickerViewModel(private val repository: Repository) : ViewModel() {

    var allSeasons = MutableLiveData<AllSeasons>()

    fun getAllSeasons(leagueId: String) {
        viewModelScope.launch {
            allSeasons.value = repository.getSeasons(leagueId)
        }
    }
}