package com.pkjain.demo.network.managers

import com.pkjain.demo.network.services.ProductsService
import com.pkjain.demo.domain.mappers.ProductsMapper
import com.pkjain.demo.model.Product
import com.pkjain.demo.model.ProductsDto
import io.reactivex.Single

/**
 * Class that connects the Data layer to Presentation, where the API objects are manipulated and observed by
 * the Views (Activity, Fragment or View)
 */
class ProductsManager {
    var productService: ProductsService = ProductsService()

    fun getProducts(pageNumber: Int, pageSize: Int): Single<List<Product>> {
        return productService.getProducts(pageNumber, pageSize)
                .onErrorResumeNext({ throwable -> Single.error(throwable) })
                .flatMap { response ->
                    if (!response.isSuccessful) {
                        Single.error(Throwable(response.code().toString()))
                    } else Single.just(response)
                }
                .map { response -> response.body() }
                .map { products -> products.products}
    }
}