package com.pkjain.demo.presentation.ui

import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.ViewGroup
import com.pkjain.demo.R
import com.pkjain.demo.model.Product
import com.pkjain.demo.presentation.ui.base.BaseDiffAdapter
import com.pkjain.demo.presentation.ui.base.VIEW_TYPE_NORMAL

class ProductListAdapter(var listener: ItemClickListener) : BaseDiffAdapter<Product, RecyclerView.ViewHolder>() {
    interface ItemClickListener {
        fun onItemClicked(product: Product)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        return if (viewType == VIEW_TYPE_NORMAL)
            ProductViewHolder(LayoutInflater.from(parent.context)
                    .inflate(R.layout.item_product, parent, false))
        else LoadingViewHolder(LayoutInflater.from(parent.context)
                .inflate(R.layout.item_loading, parent, false))
    }

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        if (getItemViewType(position) == VIEW_TYPE_NORMAL) {
            getItem(position)?.let {
                val vh: ProductViewHolder = (holder as ProductViewHolder)
                vh.setProduct(it)
                vh.itemView.setOnClickListener({ v -> listener.onItemClicked(it) })
            }
        }
    }
}