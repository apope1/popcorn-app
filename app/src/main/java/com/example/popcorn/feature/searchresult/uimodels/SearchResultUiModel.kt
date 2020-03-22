package com.example.popcorn.feature.searchresult.uimodels

import com.halcyonmobile.typedrecyclerviewadapter.RecyclerItem

data class SearchResultUiModel(
    override val id: String,
    val imageUrl: String,
    val title: String,
    val releaseDate: String,
    val voteAverage: String,
    val voteCount: String
) : RecyclerItem