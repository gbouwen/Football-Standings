package com.standings.android.layout.seasons

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.standings.android.repository.Repository

class SeasonsViewModelFactory(
    private val repository: Repository
) : ViewModelProvider.Factory {

    override fun <T : ViewModel> create(modelClass: Class<T>): T =
        SeasonsViewModel(repository) as T

}