package com.example.popcorn.feature.searchresult.uimodels

import com.example.popcorn.R
import com.example.popcorn.databinding.SearchResultItemBinding
import com.halcyonmobile.typedrecyclerviewadapter.RecyclerItem
import com.halcyonmobile.typedrecyclerviewadapter.TypedAdapterBaseFactory

class SearchResultItemFactory(private val onClickListener: (String) -> Unit) : TypedAdapterBaseFactory<SearchResultUiModel, SearchResultItemBinding>() {
    override fun bind(model: SearchResultUiModel, holder: BindingViewHolder<SearchResultItemBinding>, position: Int, payloads: List<Any>) {
        holder.binding.uiModel = model
        holder.binding.root.setOnClickListener { onClickListener(model.id) }
    }

    override fun canHandle(item: RecyclerItem) = item is SearchResultUiModel

    override fun getLayoutId() = R.layout.item_search_result
}