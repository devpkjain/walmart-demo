package com.pkjain.demo.network.services

import com.pkjain.demo.network.endpoints.ProductsApi
import com.pkjain.demo.model.ProductsDto
import com.pkjain.demo.network.retrofit.RetrofitUtil
import io.reactivex.Single
import retrofit2.Response

class ProductsService {
    var api : ProductsApi = RetrofitUtil.retrofit.create(ProductsApi::class.java)

    fun getProducts(page : Int, pageSize : Int) : Single<Response<ProductsDto>> {
        return api.getProducts(page, pageSize)
    }
}