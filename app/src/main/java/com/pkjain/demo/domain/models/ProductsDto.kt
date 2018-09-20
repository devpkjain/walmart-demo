package com.pkjain.demo.model

/**
 * Defines Product
 */
data class ProductsDto(val products: List<Product>,
                       val totalProducts: Int,
                       val pageNumber: Int,
                       val pageSize: Int,
                       val statusCode: Int
) {
    // necessary for Mapstruct check: https://github.com/mapstruct/mapstruct-examples/tree/master/mapstruct-kotlin
    constructor() : this(listOf<Product>(), 0, 0, 0, 0)
}
