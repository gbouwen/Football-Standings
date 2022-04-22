package com.standings.android

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.standings.android.model.AllLeagues
import com.standings.android.model.League
import com.standings.android.repository.Repository
import kotlinx.coroutines.launch

class MainViewModel(
    private val repository: Repository
) : ViewModel() {

    val leagues: MutableLiveData<AllLeagues> = MutableLiveData()
    val league: MutableLiveData<League> = MutableLiveData()

    fun getLeagues() {
        viewModelScope.launch {
            leagues.value = repository.getLeagues()
        }
    }

    fun getLeague(id: String) {
        viewModelScope.launch {
            league.value = repository.getLeague(id)
        }
    }

}