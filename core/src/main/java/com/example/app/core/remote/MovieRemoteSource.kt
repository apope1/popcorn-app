package com.example.app.core.remote

import com.example.app.core.api.MovieService

class MovieRemoteSource(private val service: MovieService) {
    suspend fun getSearchResults(query: String) = service.searchMovies(query = query).body()
}