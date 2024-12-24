package com.admin.clients

import org.springframework.cloud.openfeign.FeignClient
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RequestMethod

@FeignClient(
    name = "famme-service",
    url = "\${service.famme.url}"
)
interface ProductClient {

    @RequestMapping(method = [RequestMethod.GET], value = ["/products.json"])
    fun getProducts(): String
}