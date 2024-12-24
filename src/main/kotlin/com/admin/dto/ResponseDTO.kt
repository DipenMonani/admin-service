package com.admin.dto

import com.fasterxml.jackson.annotation.JsonProperty
import java.math.BigDecimal
import java.time.ZonedDateTime

data class ResponseDTO(
    val products: List<ProductDTO>
)

data class ProductDTO(
    val id: Long,
    val title: String? = null,
    val handle: String? = null,
    @JsonProperty("body_html")
    val bodyHtml: String? = null,
    @JsonProperty("published_at")
    val publishedAt: String? = null,
    @JsonProperty("created_at")
    val createdAt: String? = null,
    @JsonProperty("updated_at")
    val updatedAt: String? = null,
    val vendor: String? = null,
    @JsonProperty("product_type")
    val productType: String? = null,
    val tags: List<String>? = null,
    val variants: List<VariantDTO>? = null,
    val images: List<ImageDTO>? = null,
    val options: List<OptionDTO>? = null
)

data class VariantDTO(
    val id: Long,
    val title: String? = null,
    val option1: String? = null,
    val option2: String? = null,
    val option3: String? = null,
    val sku: String? = null,
    @JsonProperty("requires_shipping")
    val requiresShipping: String? = null,
    @JsonProperty("featured_image")
    val featuredImage: FeaturedImageDTO? = null,
    val taxable: String? = null,
    val available: Boolean? = null,
    val price: String? = null,
    val grams: BigDecimal? = null,
    @JsonProperty("compare_at_price")
    val compareAtPrice: String? = null,
    val position: Int? = null,
    @JsonProperty("product_id")
    val productId: Long? = null,
    @JsonProperty("created_at")
    val createdAt: String? = null,
    @JsonProperty("updated_at")
    val updatedAt: String? = null
)

data class ImageDTO(
    val id: Long? = null,
    @JsonProperty("created_at")
    val createdAt: String? = null,
    val position: Int? = null,
    @JsonProperty("updated_at")
    val updatedAt: String? = null,
    @JsonProperty("product_id")
    val productId: Long? = null,
    @JsonProperty("variant_ids")
    val variantIds: List<Long>? = null,
    val src: String? = null,
    val width: Int? = null,
    val height: Int? = null
)

data class OptionDTO(
    val name: String? = null,
    val position: Long? = null,
    val values: List<String>? = null
)

data class FeaturedImageDTO(
    val id: Long? = null,
    @JsonProperty("product_id")
    val productId: Long? = null,
    val position: Int? = null,
    @JsonProperty("created_at")
    val createdAt: String? = null,
    @JsonProperty("updated_at")
    val updatedAt: String? = null,
    val alt: String? = null,
    val width: Int? = null,
    val height: Int? = null,
    val src: String? = null,
    @JsonProperty("variant_ids")
    val variantIds: List<Long>? = null
)

data class PageResponse<T>(
    var totalElements: Long? = null,
    var totalPages: Int? = null,
    var number: Int? = null,
    var numberOfElements: Int? = null,
    var size: Int? = null,
    var content: List<T>? = null
)

class ProductResponse(
    var id: Long = 0,
    var title: String? = null,
    var handle: String? = null,
    var publishedAt: String? = null,
    var vendor: String? = null,
    var productType: String? = null,
    var createdAt: String? = null,
    var updatedAt: String? = null,
)