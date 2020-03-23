package com.example.popcorn.feature.main

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.app.core.Result
import com.example.app.core.model.Movie
import com.example.app.core.usecase.SearchMoviesUseCase
import com.example.popcorn.R
import com.example.popcorn.SharedPreferenceManager
import com.example.popcorn.feature.main.uimodels.TrendingItemUiModel
import com.example.popcorn.util.Event
import com.halcyonmobile.typedrecyclerviewadapter.RecyclerItem
import kotlinx.coroutines.launch

class MainViewModel(private val searchMovies: SearchMoviesUseCase, private val preferenceManager: SharedPreferenceManager) : ViewModel() {
    val searchText = MutableLiveData<String>()
    private val _uiModels = MutableLiveData<List<RecyclerItem>>()
    val uiModels: LiveData<List<RecyclerItem>> get() = _uiModels

    private val _event = MutableLiveData<Event<Action>>()
    val event: LiveData<Event<Action>> get() = _event

    private val _loading = MutableLiveData<Boolean>(false)
    val loading: LiveData<Boolean> get() = _loading

    init {
        getTrendingMovies()
    }

    fun getSuggestions() = preferenceManager.suggestions

    fun search(query: String? = null) {
        val searchQuery = query.orEmpty()
        viewModelScope.launch {
            _loading.value = true
            when (val result = searchMovies(searchQuery)) {
                is Result.Success -> {
                    if (result.value.isNullOrEmpty()) {
                        _event.value = Event(Action.ShowAlert)
                    } else {
                        if (searchQuery.isNotEmpty()) {
                            preferenceManager.suggestions = setOf(searchQuery).plus(preferenceManager.suggestions.take(MAX_SUGGESTIONS_NUMBER))
                        }
                        result.value?.let { _event.value = Event(Action.NavigateSearch(it)) }
                    }
                }
                is Result.Error -> _event.value = Event(Action.ShowAlert)
            }
            _loading.value = false
        }
    }

    private fun getTrendingMovies() {
        _uiModels.value = listOf(
            TrendingItemUiModel(R.drawable.img_trending_one),
            TrendingItemUiModel(R.drawable.img_trending_two),
            TrendingItemUiModel(R.drawable.img_trending_three),
            TrendingItemUiModel(R.drawable.img_trending_four)
        )
    }

    sealed class Action {
        data class NavigateSearch(val results: List<Movie>) : Action()
        object ShowAlert : Action()
    }

    companion object {
        private const val MAX_SUGGESTIONS_NUMBER = 9
    }
}