package com.admin.entity

import java.time.ZonedDateTime

class Product(
    var id: Long = 0,
    var title: String? = null,
    var handle: String? = null,
    var publishedAt: ZonedDateTime? = null,
    var vendor: String? = null,
    var productType: String? = null,
    var createdAt: ZonedDateTime? = ZonedDateTime.now(),
    var updatedAt: ZonedDateTime? = ZonedDateTime.now(),
)