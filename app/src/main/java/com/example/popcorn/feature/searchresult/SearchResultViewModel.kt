package com.example.popcorn.feature.searchresult

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.popcorn.feature.searchresult.uimodels.SearchResultUiModel
import com.example.popcorn.util.Event
import com.halcyonmobile.typedrecyclerviewadapter.RecyclerItem

class SearchResultViewModel : ViewModel() {
    private val _uiModels = MutableLiveData<List<RecyclerItem>>()
    val uiModels: LiveData<List<RecyclerItem>> get() = _uiModels

    private val _event = MutableLiveData<Event<Action>>()
    val event: LiveData<Event<Action>> get() = _event

    init {
        getResults()
    }

    private fun getResults() {
        _uiModels.value = listOf(
            SearchResultUiModel("1", "https://image.tmdb.org/t/p/w92/dr6x4GyyegBWtinPBzipY02J2lV.jpg", "Batman Begins", "2005", "7.6", "13747"),
            SearchResultUiModel("2", "https://image.tmdb.org/t/p/w92/kBf3g9crrADGMc2AMAMlLBgSm2h.jpg", "Batman", "1989", "7.2", "4486"),
            SearchResultUiModel("3", "https://image.tmdb.org/t/p/w92/eTMrHEhlFPHNxpqGwpGGTdAa0xV.jpg", "Batman Forever", "1995", "5.3", "3180"),
            SearchResultUiModel("4", "https://image.tmdb.org/t/p/w92/dr6x4GyyegBWtinPBzipY02J2lV.jpg", "Batman Begins", "2012", "7.6", "123643"),
            SearchResultUiModel("5", "https://image.tmdb.org/t/p/w92/dr6x4GyyegBWtinPBzipY02J2lV.jpg", "Batman Begins", "2012", "7.6", "123643"),
            SearchResultUiModel("6", "https://image.tmdb.org/t/p/w92/dr6x4GyyegBWtinPBzipY02J2lV.jpg", "Batman Begins", "2012", "7.6", "123643")
        )
    }

    fun navigateUp() {
        _event.value = Event(Action.NavigateUp)
    }

    sealed class Action {
        object NavigateUp : Action()
    }
}