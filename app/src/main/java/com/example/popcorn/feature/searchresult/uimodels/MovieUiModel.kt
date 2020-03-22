package com.example.popcorn.feature.searchresult.uimodels

import android.os.Parcelable
import com.example.app.core.BASE_IMAGE_URL
import com.example.app.core.model.Movie
import com.halcyonmobile.typedrecyclerviewadapter.RecyclerItem
import kotlinx.android.parcel.Parcelize

@Parcelize
data class MovieUiModel(
    override val id: String,
    val imageUrl: String,
    val backdropUrl: String,
    val title: String,
    val releaseDate: String,
    val voteAverage: String,
    val voteCount: String,
    val overview: String
) : RecyclerItem, Parcelable {
    companion object {
        fun map(movieResult: Movie) =
            MovieUiModel(
                movieResult.id.toString(),
                BASE_IMAGE_URL + movieResult.posterUrl,
                BASE_IMAGE_URL + movieResult.backdropUrl,
                movieResult.title.orEmpty(),
                movieResult.releaseDate?.takeWhile { it != '-' }.orEmpty(),
                movieResult.voteAverage.toString(),
                movieResult.voteCount.toString(),
                movieResult.overview.orEmpty()
            )
    }
}