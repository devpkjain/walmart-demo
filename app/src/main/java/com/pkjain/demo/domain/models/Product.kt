package com.pkjain.demo.model

import android.os.Parcelable
import android.text.Html
import com.pkjain.demo.BuildConfig
import kotlinx.android.parcel.Parcelize

/**
 *Defines Product
 */
@Parcelize
data class Product(val productId: String,
                   val productName: String?,
                   private val shortDescription: String?,
                   private val longDescription: String?,
                   val price: String?,
                   val productImage: String?,
                   val reviewRating: Float,
                   val reviewCount: Int,
                   val inStock: Boolean
) : Parcelable {
    val shortInfo = Html.fromHtml(shortDescription)
    val longInfo = Html.fromHtml(longDescription)
    fun imageUrl(): String? = productImage?.let { BuildConfig.BASE_API_URL + it }
}
