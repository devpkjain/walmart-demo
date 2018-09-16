package com.pkjain.demo.domain.mappers

import com.pkjain.demo.model.Product
import com.pkjain.demo.model.ProductsDto
import org.mapstruct.Mapper
import org.mapstruct.factory.Mappers

@Mapper
interface ProductsMapper {
    companion object {
        val Instance = Mappers.getMapper(ProductsMapper::class.java)!!
    }

//    fun map(productsDto: ProductsDto?): ProductsDto
//    fun mapList(productsDto: ProductsDto?): List<Product>
}