package com.example.popcorn.feature.main

import android.os.Bundle
import android.view.View
import com.example.popcorn.R
import com.example.popcorn.databinding.MainBinding
import com.example.popcorn.shared.PopcornFragment
import org.koin.androidx.viewmodel.ext.android.viewModel

class MainFragment : PopcornFragment<MainBinding, MainViewModel>(R.layout.fragment_main) {
    override val viewModel: MainViewModel by viewModel()

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        println()
    }
}