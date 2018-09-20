package com.pkjain.demo.presentation.ui

import android.arch.lifecycle.ViewModelProviders
import android.support.v4.app.FragmentActivity
import com.pkjain.demo.model.Product
import com.pkjain.demo.presentation.ui.base.BasePaginationViewModel
import com.pkjain.demo.utils.pagination.factory.ProductsDataSourceFactory

class ProductsViewModel constructor() : BasePaginationViewModel<ProductsDataSourceFactory, Product>() {

    init {
        dataSourceFactory = ProductsDataSourceFactory(getListener())
    }

    override fun getPageSize(): Int = 5

    fun refresh() {
        clearData()
    }


    companion object {
        @JvmStatic
        private val viewModelHolder: ViewModelHolder = ViewModelHolder()

        @JvmStatic
        fun getInstance(fragment: FragmentActivity): ProductsViewModel {
            if (viewModelHolder.viewModel == null) {
                viewModelHolder.viewModel = create(fragment = fragment)
            }

            return viewModelHolder.viewModel!!
        }

        @JvmStatic
        private fun create(fragment: FragmentActivity) = ViewModelProviders.of(fragment).get(ProductsViewModel::class.java)

    }

    data class ViewModelHolder(var viewModel: ProductsViewModel? = null)
}