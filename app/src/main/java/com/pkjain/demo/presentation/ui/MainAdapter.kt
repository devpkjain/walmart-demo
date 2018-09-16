package com.pkjain.demo.presentation.ui

import android.support.v7.widget.RecyclerView
import android.text.Html
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.bumptech.glide.Glide
import com.bumptech.glide.load.resource.drawable.DrawableTransitionOptions
import com.bumptech.glide.request.RequestOptions
import com.pkjain.demo.R
import com.pkjain.demo.model.Product
import com.pkjain.demo.presentation.ui.base.BaseDiffAdapter
import com.pkjain.demo.presentation.ui.base.VIEW_TYPE_NORMAL
import kotlinx.android.synthetic.main.item_product.view.*

class MainAdapter(var listener: ItemClickListener) : BaseDiffAdapter<Product, RecyclerView.ViewHolder>() {
    interface ItemClickListener {
        fun onItemClicked(product: Product)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        return if (viewType == VIEW_TYPE_NORMAL)
            MainViewHolder(LayoutInflater.from(parent.context)
                    .inflate(R.layout.item_product, parent, false))
        else LoadingViewHolder(LayoutInflater.from(parent.context)
                .inflate(R.layout.item_loading, parent, false))
    }

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        if (getItemViewType(position) == VIEW_TYPE_NORMAL) {
            getItem(position)?.let {
                (holder as MainViewHolder).apply {
                    titleTextView.text = it.productName
                    descriptionTextView.text = it.shortDescription?.let { Html.fromHtml(it) }
                    watchersTextView.text = it.shortDescription?.let { Html.fromHtml(it) }
                    languageTextView.text = it.price
                    starsTextView.text = it.price
                    itemView.setOnClickListener({ v -> listener.onItemClicked(it) })

                    it.imageUrl()?.apply {
                        Glide.with(holder.itemView.getContext()).load(this)
                            .apply(RequestOptions.placeholderOf(R.color.material_grey_100))
                            .transition(DrawableTransitionOptions.withCrossFade())
                            .into(holder.imageView);
                    }
                }
            }
        }
    }

    class MainViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        val titleTextView = view.list_item_title_textView
        val descriptionTextView = view.list_item_description_textView
        val descriptionTextView2 = view.list_item_description_textView2
        val imageView = view.list_item_item_imageView
        val watchersTextView = view.list_item_watchers_textView
        val languageTextView = view.list_item_language_textView
        val starsTextView = view.list_item_stars_textView
    }

}