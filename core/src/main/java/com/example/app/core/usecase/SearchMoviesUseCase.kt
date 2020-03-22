package com.example.app.core.usecase

import com.example.app.core.Result
import com.example.app.core.repository.MovieRepository

class SearchMoviesUseCase(private val movieRepository: MovieRepository) {
    suspend operator fun invoke(query: String?) = Result {
        query?.let { movieRepository.getSearchResults(it) }
    }
}