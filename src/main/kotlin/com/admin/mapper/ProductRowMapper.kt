package com.admin.mapper

import com.admin.entity.Product
import org.springframework.jdbc.core.RowMapper
import java.sql.ResultSet
import java.time.ZoneId

class ProductRowMapper : RowMapper<Product> {
    override fun mapRow(rs: ResultSet, rowNum: Int): Product {
        return Product(
            id = rs.getLong("id"),
            title = rs.getString("title"),
            handle = rs.getString("handle"),
            publishedAt = rs.getTimestamp("published_at")?.toInstant()?.atZone(ZoneId.systemDefault()),
            vendor = rs.getString("vendor"),
            productType = rs.getString("product_type"),
            createdAt = rs.getTimestamp("created_at")?.toInstant()?.atZone(ZoneId.systemDefault()),
            updatedAt = rs.getTimestamp("updated_at")?.toInstant()?.atZone(ZoneId.systemDefault())
        )
    }
}