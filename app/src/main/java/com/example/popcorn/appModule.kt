package com.example.popcorn

import com.example.popcorn.feature.HomeViewModel
import com.example.popcorn.feature.main.MainViewModel
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

fun createAppModules() = listOf(
    appModule,
    homeModule,
    mainModule
)