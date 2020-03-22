package com.example.popcorn.feature.main.uimodels

import androidx.annotation.DrawableRes
import com.halcyonmobile.typedrecyclerviewadapter.RecyclerItem

data class TrendingItemUiModel(override val id: String, @DrawableRes val imageResource: Int) : RecyclerItem