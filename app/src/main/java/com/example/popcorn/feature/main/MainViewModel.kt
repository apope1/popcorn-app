package com.example.popcorn.feature.main

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.popcorn.R
import com.example.popcorn.feature.main.uimodels.TrendingItemUiModel
import com.example.popcorn.util.Event
import com.halcyonmobile.typedrecyclerviewadapter.RecyclerItem

class MainViewModel : ViewModel() {
    val searchText = MutableLiveData<String>()
    private val _uiModels = MutableLiveData<List<RecyclerItem>>()
    val uiModels: LiveData<List<RecyclerItem>> get() = _uiModels

    private val _event = MutableLiveData<Event<Action>>()
    val event: LiveData<Event<Action>> get() = _event

    init {
        getTrendingMovies()
    }

    fun onSerchResult() {
        _event.value = Event(Action.NavigateSearch)
    }

    private fun getTrendingMovies() {
        _uiModels.value = listOf(
            TrendingItemUiModel("1", R.drawable.img_trending_one),
            TrendingItemUiModel("2", R.drawable.img_trending_two),
            TrendingItemUiModel("3", R.drawable.img_trending_three),
            TrendingItemUiModel("4", R.drawable.img_trending_four)
        )
    }

    sealed class Action {
        object NavigateSearch : Action()
    }
}