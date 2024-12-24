package com.admin.dto

class ProductRequestDTO(
    var id: Long = 0,
    var title: String? = null,
    var handle: String? = null,
    var publishedAt: String? = null,
    var vendor: String? = null,
    var productType: String? = null
)