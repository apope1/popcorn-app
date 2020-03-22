package com.example.app.core

import com.example.app.core.api.MovieService
import com.example.app.core.remote.MovieRemoteSource
import com.example.app.core.repository.MovieRepository
import com.example.app.core.usecase.SearchMoviesUseCase
import kotlinx.coroutines.Dispatchers
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import org.koin.core.qualifier.StringQualifier
import org.koin.dsl.module
import retrofit2.Retrofit
import retrofit2.converter.moshi.MoshiConverterFactory
import kotlin.coroutines.CoroutineContext

fun createCoreModules() = module {
    single {
        Retrofit.Builder()
            .baseUrl(BASE_URL)
            .client(OkHttpClient().newBuilder().addInterceptor(HttpLoggingInterceptor().apply { level = HttpLoggingInterceptor.Level.BODY }).build())
            .addConverterFactory(MoshiConverterFactory.create())
            .build()
    }
    factory { get<Retrofit>().create(MovieService::class.java) }
    factory { MovieRemoteSource(service = get()) }
    single { MovieRepository(movieRemoteSource = get()) }
    factory { SearchMoviesUseCase(movieRepository = get()) }
    single<CoroutineContext>(NETWORK_DISPATCHER) { Dispatchers.IO }
}

private val NETWORK_DISPATCHER = StringQualifier("com.popcorn.core.NETWORK_DISPATCHER")
