package com.standings.android.layout

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.standings.android.repository.Repository

class MainViewModelFactory(
    private val repository: Repository
) : ViewModelProvider.Factory {

    override fun <T : ViewModel> create(modelClass: Class<T>): T =
        MainViewModel(repository) as T

}