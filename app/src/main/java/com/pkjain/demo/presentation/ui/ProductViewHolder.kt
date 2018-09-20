package com.pkjain.demo.presentation.ui

import android.support.v7.widget.RecyclerView
import android.view.View
import com.bumptech.glide.Glide
import com.bumptech.glide.load.resource.drawable.DrawableTransitionOptions
import com.bumptech.glide.request.RequestOptions
import com.pkjain.demo.R
import com.pkjain.demo.model.Product
import kotlinx.android.synthetic.main.item_product_details.view.*

class ProductViewHolder(view: View) : RecyclerView.ViewHolder(view) {
    val productNameView = view.product_name
    val imageView = view.product_image
    val ratingView = view.product_rating
    val availabilityView = view.product_availibility
    val priceView = view.product_price
    val shortInfoTextView = view.product_short_info
    val longIndoTextView = view.product_long_info


    fun setProduct(product: Product) {
        productNameView.text = product.productName
        ratingView.text = product.reviewRating.toString()
        availabilityView.text = if (product.inStock) "Available" else null
        priceView.text = product.price
        shortInfoTextView.text = product.shortInfo
        longIndoTextView.text = product.longInfo

        product.imageUrl()?.apply {
            Glide.with(itemView.getContext()).load(this)
                    .apply(RequestOptions.placeholderOf(R.color.material_grey_100))
                    .transition(DrawableTransitionOptions.withCrossFade())
                    .into(imageView);
        }
    }
}
