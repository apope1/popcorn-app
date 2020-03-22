package com.example.popcorn.feature.main

import android.app.AlertDialog
import android.os.Bundle
import android.view.View
import androidx.lifecycle.Observer
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
        viewModel.uiModels.observe(viewLifecycleOwner, Observer(trendingAdapter::submitList))
        viewModel.event.observe(viewLifecycleOwner) {
            when (val result = it.consume()) {
                is MainViewModel.Action.NavigateSearch -> {
                    val navController = findNavController()
                    if (navController.currentDestination?.id == R.id.mainFragment) {
                        val results = result.results.toTypedArray()
                        navController.navigate(
                            MainFragmentDirections.mainToSearchResult(
                                searchResults = results,
                                query = viewModel.searchText.value.orEmpty()
                            )
                        )
                    }
                }
                is MainViewModel.Action.ShowAlert -> {
                    AlertDialog.Builder(requireContext())
                        .setTitle(getString(R.string.alert_title))
                        .setMessage(getString(R.string.alert_message))
                        .setPositiveButton(getString(R.string.ok)) { dialog, _ -> dialog.dismiss() }
                        .create()
                        .show()
                }
            }
        }
    }
}
