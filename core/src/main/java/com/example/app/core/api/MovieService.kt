package com.example.app.core.api

import com.example.app.core.API_KEY
import com.example.app.core.model.SearchResponse
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Query

interface MovieService {
    @GET("movie")
    suspend fun searchMovies(@Query("query") query: String, @Query("api_key") apiKey: String = API_KEY): Response<SearchResponse>
}