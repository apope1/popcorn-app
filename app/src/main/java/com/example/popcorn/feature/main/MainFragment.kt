package com.example.popcorn.feature.main

import android.os.Bundle
import android.view.View
import androidx.lifecycle.observe
import androidx.navigation.fragment.findNavController
import com.example.popcorn.R
import com.example.popcorn.databinding.MainBinding
import com.example.popcorn.feature.main.uimodels.TrendingItemFactory
import com.example.popcorn.shared.PopcornFragment
import com.halcyonmobile.typedrecyclerviewadapter.RecyclerAdapter
import org.koin.androidx.viewmodel.ext.android.viewModel

class MainFragment : PopcornFragment<MainBinding, MainViewModel>(R.layout.fragment_main) {
    override val viewModel: MainViewModel by viewModel()

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val trendingAdapter = RecyclerAdapter().apply {
            addCellFactories(TrendingItemFactory())
        }
        binding.trendingList.adapter = trendingAdapter
        viewModel.uiModels.observe(viewLifecycleOwner) {
            trendingAdapter.submitList(it)
        }
        viewModel.event.observe(viewLifecycleOwner) {
            when (it.consume()) {
                is MainViewModel.Action.NavigateSearch -> {
                    val navController = findNavController()
                    if (navController.currentDestination?.id == R.id.mainFragment) {
                        navController.navigate(MainFragmentDirections.mainToSearchResult())
                    }
                }
            }
        }
    }
}
