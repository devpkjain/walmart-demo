package com.pkjain.demo.utils.pagination.factory

import android.arch.paging.DataSource
import com.pkjain.demo.model.Product
import com.pkjain.demo.utils.pagination.datasource.ProductsDataSource
import com.pkjain.demo.utils.pagination.datasource.base.OnDataSourceLoading

/**
 * Factory class that handles the creation of DataSources
 */
class ProductsDataSourceFactory(var loading: OnDataSourceLoading) : DataSource.Factory<Int, Product>() {
    lateinit var source: ProductsDataSource

    override fun create(): DataSource<Int, Product>? {
        if (::source.isInitialized) source.invalidate()
        source = ProductsDataSource()
        source.onDataSourceLoading = loading
        return source
    }
}