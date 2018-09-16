package com.pkjain.demo.network.endpoints

import com.pkjain.demo.model.ProductsDto
import io.reactivex.Single
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Path

private const val PREFIX = "/walmartproducts/"

interface ProductsApi {

    @GET(PREFIX + "{pageNumber}/{pageSize}")
    fun getProducts(@Path("pageNumber") pageNumber: Int,
                    @Path("pageSize") pageSize: Int
    ): Single<Response<ProductsDto>>
}