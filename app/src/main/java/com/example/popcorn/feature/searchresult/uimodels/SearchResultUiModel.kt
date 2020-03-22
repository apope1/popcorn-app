package com.example.popcorn.feature.searchresult.uimodels

import com.example.app.core.BASE_IMAGE_URL
import com.example.app.core.model.Movie
import com.halcyonmobile.typedrecyclerviewadapter.RecyclerItem

data class SearchResultUiModel(
    override val id: String,
    val imageUrl: String,
    val title: String,
    val releaseDate: String,
    val voteAverage: String,
    val voteCount: String
) : RecyclerItem {
    companion object {
        fun map(movieResult: Movie) =
            SearchResultUiModel(
                movieResult.id.toString(),
                BASE_IMAGE_URL + movieResult.posterUrl,
                movieResult.title,
                movieResult.releaseDate.takeWhile { it != '-' },
                movieResult.voteAverage.toString(),
                movieResult.voteCount.toString()
            )
    }
}