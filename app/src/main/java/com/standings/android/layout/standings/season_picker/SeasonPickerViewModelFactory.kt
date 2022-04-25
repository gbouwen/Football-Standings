package com.standings.android.layout.standings.season_picker

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.standings.android.repository.Repository

class SeasonPickerViewModelFactory(private val repository: Repository) : ViewModelProvider.Factory {

    override fun <T : ViewModel> create(modelClass: Class<T>): T =
        SeasonPickerViewModel(repository) as T
}