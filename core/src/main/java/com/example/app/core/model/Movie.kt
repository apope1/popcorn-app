package com.example.app.core.model

import android.os.Parcelable
import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass
import kotlinx.android.parcel.Parcelize

@JsonClass(generateAdapter = true)
@Parcelize
data class Movie(
    @Json(name = "id")
    val id: Int,
    @Json(name = "poster_path")
    val posterUrl: String,
    @Json(name = "title")
    val title: String,
    @Json(name = "release_date")
    val releaseDate: String,
    @Json(name = "vote_average")
    val voteAverage: Float,
    @Json(name = "vote_count")
    val voteCount: Int,
    @Json(name = "overview")
    val overview: String
) : Parcelable