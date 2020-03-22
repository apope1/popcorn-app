package com.example.popcorn.feature.detail

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.popcorn.feature.searchresult.uimodels.MovieUiModel
import com.example.popcorn.util.Event

class MovieDetailViewModel(val movie: MovieUiModel) : ViewModel() {

    private val _event = MutableLiveData<Event<Action>>()
    val event: LiveData<Event<Action>> get() = _event

    fun navigateUp() {
        _event.value = Event(Action.NavigateUp)
    }

    sealed class Action {
        object NavigateUp : Action()
    }
}