package com.pkjain.demo.model

import com.pkjain.demo.BuildConfig

/**
 *Defines Product
 */
data class Product(val productId: String,
                   val productName: String?,
                   val shortDescription: String?,
                   val longDescription: String?,
                   val price: String?,
                   val productImage: String?,
                   val reviewRating: Float,
                   val reviewCount: Int,
                   val inStock: Boolean
) {
    fun imageUrl(): String? = productImage?.let { BuildConfig.BASE_API_URL + it }
}
