package com.admin.repository

import com.admin.entity.Product
import com.admin.mapper.ProductRowMapper
import org.springframework.jdbc.core.JdbcTemplate
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate
import org.springframework.stereotype.Repository
import java.sql.Timestamp


@Repository
class ProductRepository(
    private val namedParameterJdbcTemplate: NamedParameterJdbcTemplate,
    private val jdbcTemplate: JdbcTemplate
) {

    fun insertProduct(product: Product): Int {
        val sql = if (product.id != 0L) {
            "INSERT INTO products (id, title, handle, published_at, vendor, product_type, created_at, updated_at) " +
                    "VALUES (:id, :title, :handle, :publishedAt, :vendor, :productType, :createdAt, :updatedAt)"

        } else {
            "INSERT INTO products (title, handle, published_at, vendor, product_type, created_at, updated_at) " +
                    "VALUES (:title, :handle, :publishedAt, :vendor, :productType, :createdAt, :updatedAt)"

        }
        val publishedAt: Timestamp? = product.publishedAt?.let { Timestamp.from(it.toInstant()) }
        val createdAt: Timestamp? = product.createdAt?.let { Timestamp.from(it.toInstant()) }
        val updatedAt: Timestamp? = product.updatedAt?.let { Timestamp.from(it.toInstant()) }

        val parameters = MapSqlParameterSource()
        if (product.id != 0L) {
            parameters.addValue("id", product.id)
        }
        parameters.addValue("title", product.title)
        parameters.addValue("handle", product.handle)
        parameters.addValue("publishedAt", publishedAt)
        parameters.addValue("vendor", product.vendor)
        parameters.addValue("productType", product.productType)
        parameters.addValue("createdAt", createdAt)
        parameters.addValue("updatedAt", updatedAt)

        return namedParameterJdbcTemplate.update(sql, parameters)
    }

    fun isProductExist(id: Long): Boolean {
        val sql = "SELECT COUNT(*) FROM products WHERE id = ?"
        val count = jdbcTemplate.queryForObject(sql, Integer::class.java, id)
        return count != null && count > 0
    }

    fun updateProduct(product: Product): Int {
        val sql =
            "UPDATE public.products " +
                    "SET title= :title,  " +
                    "handle= :handle,  " +
                    "published_at= :publishedAt,  " +
                    "vendor= :vendor,  " +
                    "product_type= :productType,  " +
                    "created_at= :createdAt,  " +
                    "updated_at= :updatedAt " +
                    "WHERE id= :id"

        val publishedAt: Timestamp? = product.publishedAt?.let { Timestamp.from(it.toInstant()) }
        val createdAt: Timestamp? =  product.createdAt?.let { Timestamp.from(it.toInstant()) }
        val updatedAt: Timestamp? =  product.updatedAt?.let { Timestamp.from(it.toInstant()) }

        val parameters = MapSqlParameterSource()
        parameters.addValue("id", product.id)
        parameters.addValue("title", product.title)
        parameters.addValue("handle", product.handle)
        parameters.addValue("publishedAt", publishedAt)
        parameters.addValue("vendor", product.vendor)
        parameters.addValue("productType", product.productType)
        parameters.addValue("createdAt", createdAt)
        parameters.addValue("updatedAt", updatedAt)

        return namedParameterJdbcTemplate.update(sql, parameters)
    }

    fun getProducts(page: Int, size: Int): List<Product> {
        val offset = if (page > 0) ((page) * size) else 0
        val sql = "SELECT * FROM products order by created_at desc LIMIT ? OFFSET ?"
        return jdbcTemplate.query(sql, ProductRowMapper(), size, offset)
    }

    fun getTotalProducts(): Int {
        val sql = "SELECT COUNT(*) FROM products"
        return jdbcTemplate.queryForObject(sql, Int::class.java) ?: 0
    }
}