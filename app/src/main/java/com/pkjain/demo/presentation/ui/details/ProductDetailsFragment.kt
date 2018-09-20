package com.pkjain.demo.presentation.ui.details

import android.os.Bundle
import android.support.v4.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.pkjain.demo.R
import com.pkjain.demo.model.Product
import com.pkjain.demo.presentation.ui.ProductViewHolder


/**
 *
 */
class ProductDetailsFragment : Fragment() {
    private var product: Product? = null
    private var title: String? = null
    private var page: Int = 0
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        getArguments()?.apply {
            page = getInt("someInt", 0) ?: 0
            title = getString("someTitle") ?: "Name Title"
        }
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        super.onCreateView(inflater, container, savedInstanceState)

        val view = ProductViewHolder(LayoutInflater.from(context)
                .inflate(R.layout.item_product_details, container, false)).apply {
            product?.let {
                setProduct(it)
            }
        }
        return view.itemView;
    }

    companion object {

        fun newInstance(page: Int, string: String): ProductDetailsFragment {
            val fragment = ProductDetailsFragment()
            val args = Bundle()
            args.putInt("someInt", page)
            args.putString("someTitle", string)
            fragment.setArguments(args)
            return fragment
        }

        // newInstance constructor for creating fragment with arguments
        fun newInstance(page: Int, product: Product): ProductDetailsFragment {
            val fragment = ProductDetailsFragment()
            fragment.product = product
            val args = Bundle()
            args.putInt("someInt", page)
            args.putString("someTitle", product.productName).toString().substring(0, 6)
            fragment.setArguments(args)
            return fragment
        }
    }
}