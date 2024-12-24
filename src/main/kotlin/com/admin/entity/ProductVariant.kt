package com.admin.entity

import java.math.BigDecimal
import java.time.ZonedDateTime

class ProductVariant(
    var id: Long = 0,
    var title: String? = null,
    var sku: String? = null,
    var requiresShipping: Boolean? = null,
    var taxable: Boolean? = null,
    var available: Boolean? = null,
    var price: BigDecimal? = null,
    var grams: BigDecimal? = null,
    var compareAtPrice: BigDecimal? = null,
    var position: Int? = null,
    var productId: Long? = null,
    var featuredImage: String? = null,
    var createdAt: ZonedDateTime? = null,
    var updatedAt: ZonedDateTime? = null,
)