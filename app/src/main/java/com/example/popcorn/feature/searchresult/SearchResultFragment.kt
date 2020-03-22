package com.example.popcorn.feature.searchresult

import android.os.Bundle
import android.view.View
import androidx.lifecycle.Observer
import androidx.lifecycle.observe
import androidx.navigation.fragment.findNavController
import androidx.navigation.fragment.navArgs
import com.example.popcorn.R
import com.example.popcorn.databinding.SearchResultBinding
import com.example.popcorn.feature.searchresult.uimodels.SearchResultItemFactory
import com.example.popcorn.shared.PopcornFragment
import com.halcyonmobile.typedrecyclerviewadapter.RecyclerAdapter
import org.koin.androidx.viewmodel.ext.android.viewModel
import org.koin.core.parameter.parametersOf

class SearchResultFragment : PopcornFragment<SearchResultBinding, SearchResultViewModel>(R.layout.fragment_search_result) {

    private val args by navArgs<SearchResultFragmentArgs>()
    override val viewModel: SearchResultViewModel by viewModel { parametersOf(args.searchResults, args.query) }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val resultsAdapter = RecyclerAdapter().apply {
            addCellFactories(SearchResultItemFactory {})
        }
        binding.resultList.adapter = resultsAdapter
        viewModel.uiModels.observe(viewLifecycleOwner, Observer(resultsAdapter::submitList))
        viewModel.event.observe(viewLifecycleOwner) {
            when (it.consume()) {
                is SearchResultViewModel.Action.NavigateUp -> {
                    val navController = findNavController()
                    if (navController.currentDestination?.id == R.id.searchResultFragment) {
                        navController.navigateUp()
                    }
                }
            }
        }
    }
}