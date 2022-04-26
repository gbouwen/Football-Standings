package com.standings.android.layout.standings

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.standings.android.model.league.League
import com.standings.android.model.season.AllSeasons
import com.standings.android.model.standings.AllStandings
import com.standings.android.repository.Repository
import kotlinx.coroutines.launch
import retrofit2.Response

class StandingsViewModel(private val repository: Repository) : ViewModel() {

    val league: MutableLiveData<Response<League>> = MutableLiveData()
    val allSeasons: MutableLiveData<Response<AllSeasons>> = MutableLiveData()
    val allStandings: MutableLiveData<Response<AllStandings>> = MutableLiveData()

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

    fun getStandings(id: String, year: Int) {
        viewModelScope.launch {
            allStandings.value = repository.getStandings(id, year)
        }
    }

}