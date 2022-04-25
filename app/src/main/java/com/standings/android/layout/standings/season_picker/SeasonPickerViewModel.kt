package com.standings.android.layout.standings.season_picker

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.standings.android.repository.Repository
import kotlinx.coroutines.launch

class SeasonPickerViewModel(private val repository: Repository) : ViewModel() {

    var years = MutableLiveData<List<Int>>()

    fun getYears(leagueId: String) {
        viewModelScope.launch {
            val allSeasons = repository.getSeasons(leagueId)
            years.value = allSeasons.data.seasons.map { season ->
                season.year
            }
        }
    }
}