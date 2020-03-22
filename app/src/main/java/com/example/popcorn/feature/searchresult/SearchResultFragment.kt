package com.example.popcorn.feature.searchresult

import com.example.popcorn.R
import com.example.popcorn.databinding.SearchResultBinding
import com.example.popcorn.shared.PopcornFragment
import org.koin.androidx.viewmodel.ext.android.viewModel

class SearchResultFragment : PopcornFragment<SearchResultBinding, SearchResultViewModel>(R.layout.fragment_search_result) {
    override val viewModel by viewModel<SearchResultViewModel>()
}