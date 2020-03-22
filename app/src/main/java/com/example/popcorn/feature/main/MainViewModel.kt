package com.example.popcorn.feature.main

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.app.core.Result
import com.example.app.core.model.Movie
import com.example.app.core.usecase.SearchMoviesUseCase
import com.example.popcorn.R
import com.example.popcorn.feature.main.uimodels.TrendingItemUiModel
import com.example.popcorn.util.Event
import com.halcyonmobile.typedrecyclerviewadapter.RecyclerItem
import kotlinx.coroutines.launch

class MainViewModel(private val searchMovies: SearchMoviesUseCase) : ViewModel() {
    val searchText = MutableLiveData<String>()
    private val _uiModels = MutableLiveData<List<RecyclerItem>>()
    val uiModels: LiveData<List<RecyclerItem>> get() = _uiModels

    private val _event = MutableLiveData<Event<Action>>()
    val event: LiveData<Event<Action>> get() = _event

    init {
        getTrendingMovies()
    }

    fun onSearchResult(results: List<Movie>) {
        _event.value = Event(Action.NavigateSearch(results))
    }

    fun search() {
        viewModelScope.launch {
            when (val result = searchMovies(searchText.value)) {
                is Result.Success -> {
                    if (result.value.isNullOrEmpty()) {
                        Unit
                    } else {
                        result.value?.let { onSearchResult(it) }
                    }
                }
                is Result.Error -> Unit
            }
        }
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
        data class NavigateSearch(val results: List<Movie>) : Action()
    }
}