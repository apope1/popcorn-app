package com.example.popcorn.feature.main.uimodels

import androidx.annotation.DrawableRes
import com.halcyonmobile.typedrecyclerviewadapter.RecyclerItem

data class TrendingItemUiModel(@DrawableRes val imageResource: Int) : RecyclerItem {
    override val id: String get() = imageResource.toString()
}