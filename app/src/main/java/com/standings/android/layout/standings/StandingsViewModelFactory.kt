package com.standings.android.layout.standings

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.standings.android.repository.Repository

class StandingsViewModelFactory(
    private val repository: Repository
) : ViewModelProvider.Factory {

    override fun <T : ViewModel> create(modelClass: Class<T>): T =
        StandingsViewModel(repository) as T

}