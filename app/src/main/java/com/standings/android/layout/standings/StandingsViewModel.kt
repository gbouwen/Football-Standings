package com.standings.android.layout.standings

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.standings.android.model.league.League
import com.standings.android.model.season.AllSeasons
import com.standings.android.model.standings.AllStandings
import com.standings.android.model.standings.Standings
import com.standings.android.repository.Repository
import kotlinx.coroutines.launch
import retrofit2.Response

class StandingsViewModel(private val repository: Repository) : ViewModel() {

    val league: MutableLiveData<Response<League>> = MutableLiveData()
    val allSeasons: MutableLiveData<Response<AllSeasons>> = MutableLiveData()
    val allStandings: MutableLiveData<Response<AllStandings>> = MutableLiveData()
    val rewards: MutableLiveData<List<Standings.Reward>> = MutableLiveData()

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
            val response = repository.getStandings(id, year)
            allStandings.value = response
            if (response.isSuccessful) {
                val standings = response.body()!!.data.standings
                rewards.value = standings.filter {
                    it.reward != null
                }.map {
                    Standings.Reward(
                        description = it.reward!!.description.trim(),
                        color = it.reward.color.trim()
                    )
                }.distinct()
            }
        }
    }

}