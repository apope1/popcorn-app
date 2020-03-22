package com.example.popcorn.feature.main.uimodels

import com.example.popcorn.R
import com.example.popcorn.databinding.TrendingItemBinding
import com.halcyonmobile.typedrecyclerviewadapter.RecyclerItem
import com.halcyonmobile.typedrecyclerviewadapter.TypedAdapterBaseFactory

class TrendingItemFactory : TypedAdapterBaseFactory<TrendingItemUiModel, TrendingItemBinding>() {
    override fun bind(model: TrendingItemUiModel, holder: BindingViewHolder<TrendingItemBinding>, position: Int, payloads: List<Any>) {
        holder.binding.uiModel = model
    }

    override fun canHandle(item: RecyclerItem) = item is TrendingItemUiModel

    override fun getLayoutId() = R.layout.item_trending
}