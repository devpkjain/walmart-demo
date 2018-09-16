package com.pkjain.demo.presentation.ui

import com.pkjain.demo.model.Product
import com.pkjain.demo.presentation.ui.base.BasePaginationViewModel
import com.pkjain.demo.utils.pagination.factory.ProductsDataSourceFactory

class MainViewModel : BasePaginationViewModel<ProductsDataSourceFactory, Product>() {
    init {
        dataSourceFactory = ProductsDataSourceFactory(getListener())
    }

    override fun getPageSize(): Int = 5

    /**
     * Handles a new user search
     */
    fun refresh() {
        clearData()
    }
}