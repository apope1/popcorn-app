package com.example.popcorn.feature.detail

import com.example.popcorn.R
import com.example.popcorn.databinding.MovieDetailBinding
import com.example.popcorn.shared.PopcornFragment
import org.koin.androidx.viewmodel.ext.android.viewModel

class MovieDetailFragment : PopcornFragment<MovieDetailBinding, MovieDetailViewModel>(R.layout.fragment_movie_detail) {
    override val viewModel by viewModel<MovieDetailViewModel>()
}