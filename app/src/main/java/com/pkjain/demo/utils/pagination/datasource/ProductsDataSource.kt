package com.pkjain.demo.utils.pagination.datasource

import android.annotation.SuppressLint
import com.pkjain.demo.model.Product
import com.pkjain.demo.network.managers.ProductsManager
import com.pkjain.demo.utils.pagination.datasource.base.BaseDataSource
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers

/**
 * Class that handles how to retrieve data for the recyclerview
 * @see BaseDataSource
 */
class ProductsDataSource() : BaseDataSource<Product>() {
    val manager: ProductsManager = ProductsManager()
    val firstPageNumber = 1
    @SuppressLint("CheckResult")
    override fun loadInitialData(params: LoadInitialParams<Int>, callback: LoadInitialCallback<Int, Product>) {
        // in the initial load, we will start at page 0, and retrieve the number of pages in the params.requestLoadSize
        manager.getProducts(firstPageNumber, params.requestedLoadSize)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .doOnSubscribe(this::addDisposable)
                .subscribe(
                        { items ->
                            submitInitialData(items, params, callback)
                        },
                        { error -> submitInitialError(error) }
                )
    }

    @SuppressLint("CheckResult")
    override fun loadAditionalData(params: LoadParams<Int>, callback: LoadCallback<Int, Product>) {
        manager.getProducts(params.key, params.requestedLoadSize)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .doOnSubscribe(this::addDisposable)
                .subscribe(
                        { items -> submitData(items, params, callback) },
                        { error -> submitError(error) }
                )
    }
}