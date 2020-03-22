package com.example.popcorn.feature

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import com.example.popcorn.HomeBinding
import com.example.popcorn.R
import org.koin.androidx.viewmodel.ext.android.viewModel

class HomeActivity : AppCompatActivity() {
    val viewModel by viewModel<HomeViewModel>()
    private lateinit var binding: HomeBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        setTheme(R.style.BaseTheme_App)
        super.onCreate(savedInstanceState)
        binding = DataBindingUtil.setContentView(this, R.layout.activity_home)
        binding.lifecycleOwner = this
        binding.viewModel = viewModel
    }

}