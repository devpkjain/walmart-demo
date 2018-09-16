package com.pkjain.demo.domain.models

/**
 *
 */
data class PageInfo(
        var pageNumber: Int,
        val pageSize: Int,
        val totalProducts: Int
        )