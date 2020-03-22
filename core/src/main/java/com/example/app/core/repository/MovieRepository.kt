package com.example.app.core.repository

import com.example.app.core.remote.MovieRemoteSource

class MovieRepository(private val movieRemoteSource: MovieRemoteSource) {
    suspend fun getSearchResults(query: String) = movieRemoteSource.getSearchResults(query)?.results
}