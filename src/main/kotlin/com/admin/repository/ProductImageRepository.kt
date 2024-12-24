package com.admin.repository

import com.admin.entity.ProductImage
import org.springframework.jdbc.core.JdbcTemplate
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate
import org.springframework.stereotype.Repository
import java.sql.Timestamp


@Repository
class ProductImageRepository(
    private val namedParameterJdbcTemplate: NamedParameterJdbcTemplate,
    private val jdbcTemplate: JdbcTemplate
) {
    fun insertProductImage(productImage: ProductImage): Int {
        val sql =
            "INSERT INTO product_image (id, position, product_id, src, width, height, created_at, updated_at) " +
                    "VALUES (:id, :position, :productId, :src, :width, :height, :createdAt, :updatedAt)"

        val createdAt: Timestamp? = productImage.createdAt?.let { Timestamp.from(it.toInstant()) }
        val updatedAt: Timestamp? = productImage.updatedAt?.let { Timestamp.from(it.toInstant()) }

        val parameters = MapSqlParameterSource()
        parameters.addValue("id", productImage.id)
        parameters.addValue("position", productImage.position)
        parameters.addValue("productId", productImage.productId)
        parameters.addValue("src", productImage.src)
        parameters.addValue("width", productImage.width)
        parameters.addValue("height", productImage.height)
        parameters.addValue("createdAt", createdAt)
        parameters.addValue("updatedAt", updatedAt)

        return namedParameterJdbcTemplate.update(sql, parameters)
    }

    fun isProductImageExist(id: Long): Boolean {
        val sql = "SELECT COUNT(*) FROM product_image WHERE id = ?"
        val count = jdbcTemplate.queryForObject(sql, Integer::class.java, id)
        return count != null && count > 0
    }
}