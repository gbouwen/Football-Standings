package com.standings.android

import android.util.Log
import android.util.Log.VERBOSE
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.standings.android.model.League
import com.standings.android.model.Post
import com.standings.android.repository.Repository
import kotlinx.coroutines.launch

class MainViewModel(
    private val repository: Repository
) : ViewModel() {

    //    val leagues: MutableLiveData<List<League>> = MutableLiveData()
    val league: MutableLiveData<League> = MutableLiveData()
    val post: MutableLiveData<Post> = MutableLiveData()

//    fun getLeagues() {
//        viewModelScope.launch {
//            leagues.value = repository.getLeagues()
//        }
//    }

//    fun getLeague(id: String) {
//        viewModelScope.launch {
//            league.value = repository.getLeague(id)
//        }
//    }

    fun getPost() {
        viewModelScope.launch {
            post.value = repository.getPost()
        }
    }

}