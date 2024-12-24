package com.admin.controller

import com.admin.dto.ProductRequestDTO
import com.admin.service.ProductService
import com.admin.utils.Response
import io.github.wimdeblauwe.htmx.spring.boot.mvc.HtmxRequest
import io.github.wimdeblauwe.htmx.spring.boot.mvc.HtmxResponse
import org.springframework.stereotype.Controller
import org.springframework.ui.Model
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.ModelAttribute
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestParam

@Controller
class ProductController(
    private val productService: ProductService
) {

    @GetMapping("/view/product/list")
    fun viewProductList(
        model: Model,
        @RequestParam(defaultValue = "0") page: Int,
        @RequestParam(defaultValue = "10") size: Int,
        htmxRequest: HtmxRequest
    ): HtmxResponse {
        model.addAttribute("pageResponse", productService.getProducts(page, size))
        return Response.content(htmxRequest, "product/product")
    }

    @PostMapping("/add/product")
    fun saveProduct(
        model: Model,
        htmxRequest: HtmxRequest,
        @ModelAttribute request: ProductRequestDTO
    ): HtmxResponse {
        productService.addProduct(request)
        return Response.redirect("/view/product/list", "Product saved successfully")
    }
}