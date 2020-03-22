package com.example.popcorn

import com.example.app.core.createCoreModules
import com.example.app.core.model.Movie
import com.example.popcorn.feature.HomeViewModel
import com.example.popcorn.feature.detail.MovieDetailViewModel
import com.example.popcorn.feature.main.MainViewModel
import com.example.popcorn.feature.searchresult.SearchResultViewModel
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module

private val appModule = module {
    single { SharedPreferenceManager(context = get()) }
}

private val homeModule = module {
    viewModel { HomeViewModel() }
}

private val mainModule = module {
    viewModel { MainViewModel(searchMovies = get()) }
}

private val searchResultModule = module {
    viewModel { (searchResults: Array<Movie>, query: String) -> SearchResultViewModel(searchResults = searchResults, query = query) }
}

private val movieDetailModule = module {
    viewModel { MovieDetailViewModel() }
}

fun createAppModules() = listOf(
    appModule,
    homeModule,
    searchResultModule,
    movieDetailModule,
    mainModule
).plus(createCoreModules())