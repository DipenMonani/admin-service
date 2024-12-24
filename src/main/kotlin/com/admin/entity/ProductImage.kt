package com.admin.entity

import java.time.ZonedDateTime

class ProductImage(
    var id: Long = 0,
    var position: Int? = null,
    var productId: Long? = null,
    var src: String? = null,
    var width: Int? = null,
    var height: Int? = null,
    var createdAt: ZonedDateTime? = null,
    var updatedAt: ZonedDateTime? = null,
)