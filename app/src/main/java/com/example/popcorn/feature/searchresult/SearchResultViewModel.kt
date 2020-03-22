package com.example.popcorn.feature.searchresult

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.app.core.model.Movie
import com.example.popcorn.feature.searchresult.uimodels.MovieUiModel
import com.example.popcorn.util.Event
import com.halcyonmobile.typedrecyclerviewadapter.RecyclerItem

class SearchResultViewModel(searchResults: Array<Movie>, val query: String) : ViewModel() {
    private val _uiModels = MutableLiveData<List<RecyclerItem>>()
    val uiModels: LiveData<List<RecyclerItem>> get() = _uiModels

    private val _event = MutableLiveData<Event<Action>>()
    val event: LiveData<Event<Action>> get() = _event

    init {
        _uiModels.value = searchResults.map { MovieUiModel.map(it) }
    }

    fun navigateUp() {
        _event.value = Event(Action.NavigateUp)
    }

    sealed class Action {
        object NavigateUp : Action()
    }
}