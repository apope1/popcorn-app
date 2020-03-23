package com.example.popcorn.feature.detail

import android.os.Bundle
import android.view.View
import androidx.lifecycle.observe
import androidx.navigation.fragment.findNavController
import androidx.navigation.fragment.navArgs
import com.example.popcorn.R
import com.example.popcorn.databinding.MovieDetailBinding
import com.example.popcorn.shared.PopcornFragment
import org.koin.androidx.viewmodel.ext.android.viewModel
import org.koin.core.parameter.parametersOf

class MovieDetailFragment : PopcornFragment<MovieDetailBinding, MovieDetailViewModel>(R.layout.fragment_movie_detail) {

    private val args by navArgs<MovieDetailFragmentArgs>()
    override val viewModel: MovieDetailViewModel by viewModel { parametersOf(args.movie) }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        viewModel.event.observe(viewLifecycleOwner) {
            when (it.consume()) {
                is MovieDetailViewModel.Action.NavigateUp -> {
                    val navController = findNavController()
                    if (navController.currentDestination?.id == R.id.movieDetailFragment) {
                        navController.navigate(MovieDetailFragmentDirections.detailToMain())
                    }
                }
            }
        }
    }
}