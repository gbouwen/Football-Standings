package com.standings.android.layout.standings

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.standings.android.model.standings.AllStandings
import com.standings.android.repository.Repository
import kotlinx.coroutines.launch

class StandingsViewModel(private val repository: Repository) : ViewModel() {

    val allStandings: MutableLiveData<AllStandings> = MutableLiveData()

    fun getStandings(id: String, season: String) {
        viewModelScope.launch {
            allStandings.value = repository.getStandings(id, season)
        }
    }

}