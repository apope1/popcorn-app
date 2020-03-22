package com.example.popcorn.feature

import android.os.Bundle
import android.view.View
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
        binding.root.systemUiVisibility = View.SYSTEM_UI_FLAG_LAYOUT_STABLE or View.SYSTEM_UI_FLAG_LAYOUT_FULLSCREEN
        binding.lifecycleOwner = this
        binding.viewModel = viewModel
    }

}