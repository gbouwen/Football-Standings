package com.standings.android.layout.seasons

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.standings.android.model.league.League
import com.standings.android.model.season.AllSeasons
import com.standings.android.repository.Repository
import kotlinx.coroutines.launch

class SeasonsViewModel(private val repository: Repository) : ViewModel() {

    val league: MutableLiveData<League> = MutableLiveData()
    val allSeasons: MutableLiveData<AllSeasons> = MutableLiveData()

    fun getLeague(id: String) {
        viewModelScope.launch {
            league.value = repository.getLeague(id)
        }
    }

    fun getSeasons(id: String) {
        viewModelScope.launch {
            allSeasons.value = repository.getSeasons(id)
        }
    }

}