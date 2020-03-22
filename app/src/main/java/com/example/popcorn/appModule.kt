package com.example.popcorn

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
    viewModel { MainViewModel() }
}

private val searchResultModule = module {
    viewModel { SearchResultViewModel() }
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
)