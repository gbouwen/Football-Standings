package com.standings.android.layout.seasons

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.standings.android.model.League
import com.standings.android.repository.Repository
import kotlinx.coroutines.launch

class SeasonsViewModel(private val repository: Repository) : ViewModel() {

    val league: MutableLiveData<League> = MutableLiveData()

    fun getLeague(id: String) {
        viewModelScope.launch {
            league.value = repository.getLeague(id)
        }
    }

}