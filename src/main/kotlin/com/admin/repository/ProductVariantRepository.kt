package com.admin.repository

import com.admin.entity.Product
import com.admin.entity.ProductVariant
import org.springframework.jdbc.core.JdbcTemplate
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate
import org.springframework.stereotype.Repository
import java.sql.Timestamp


@Repository
class ProductVariantRepository(
    private val namedParameterJdbcTemplate: NamedParameterJdbcTemplate,
    private val jdbcTemplate: JdbcTemplate
) {
    fun insertProductVariant(productVariant: ProductVariant): Int {
        val sql =
            "INSERT INTO product_variant (id, title, sku, requires_shipping, taxable, featured_image, available, price, grams, compare_at_price, position, product_id, created_at, updated_at) " +
                    "VALUES (:id, :title, :sku, :requiresShipping, :taxable, :featuredImage, :available, :price, :grams, :compareAtPrice, :position, :productId, :createdAt, :updatedAt)"

        val createdAt: Timestamp? =  productVariant.createdAt?.let { Timestamp.from(it.toInstant()) }
        val updatedAt: Timestamp? =  productVariant.updatedAt?.let { Timestamp.from(it.toInstant()) }

        val parameters = MapSqlParameterSource()
        parameters.addValue("id", productVariant.id)
        parameters.addValue("title", productVariant.title)
        parameters.addValue("sku", productVariant.sku)
        parameters.addValue("requiresShipping", productVariant.requiresShipping)
        parameters.addValue("taxable", productVariant.taxable)
        parameters.addValue("featuredImage", productVariant.featuredImage)
        parameters.addValue("available", productVariant.available)
        parameters.addValue("price", productVariant.price)
        parameters.addValue("grams", productVariant.grams)
        parameters.addValue("compareAtPrice", productVariant.compareAtPrice)
        parameters.addValue("position", productVariant.position)
        parameters.addValue("productId", productVariant.productId)
        parameters.addValue("createdAt", createdAt)
        parameters.addValue("updatedAt", updatedAt)

        return namedParameterJdbcTemplate.update(sql, parameters)
    }

    fun isProductVariantExist(id: Long): Boolean {
        val sql = "SELECT COUNT(*) FROM product_variant WHERE id = ?"
        val count = jdbcTemplate.queryForObject(sql, Integer::class.java, id)
        return count != null && count > 0
    }
}