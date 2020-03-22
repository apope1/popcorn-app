package com.example.popcorn.feature.main

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel

class MainViewModel : ViewModel() {
    val text = MutableLiveData<String>("Main")
}