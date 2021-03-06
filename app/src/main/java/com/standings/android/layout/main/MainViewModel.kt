package com.standings.android.layout.main

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.standings.android.model.league.AllLeagues
import com.standings.android.repository.Repository
import kotlinx.coroutines.launch
import retrofit2.Response

class MainViewModel(
    private val repository: Repository
) : ViewModel() {

    val leagues: MutableLiveData<Response<AllLeagues>> = MutableLiveData()

    fun getLeagues() {
        viewModelScope.launch {
            leagues.value = repository.getLeagues()
        }
    }

}