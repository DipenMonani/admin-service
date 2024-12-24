package com.admin.service

import com.admin.dto.PageResponse
import com.admin.dto.ProductDTO
import com.admin.dto.ProductRequestDTO
import com.admin.dto.ProductResponse
import com.admin.entity.Product
import com.admin.entity.ProductImage
import com.admin.entity.ProductVariant
import com.admin.mapper.ProductMapper
import com.admin.repository.ProductImageRepository
import com.admin.repository.ProductRepository
import com.admin.repository.ProductVariantRepository
import org.slf4j.LoggerFactory
import org.springframework.stereotype.Service

@Service
class ProductService(
    private val productRepository: ProductRepository,
    private val productVariantRepository: ProductVariantRepository,
    private val productImageRepository: ProductImageRepository,
    private val productMapper: ProductMapper
) {

    private val log = LoggerFactory.getLogger(javaClass)

    fun saveProducts(products: List<ProductDTO>?) {
        log.info("Invoke saveProducts function.")
        try {
            products?.forEach { product ->
                val productEntity = productMapper.toProductEntity(product)
                saveProduct(productEntity)
                val productVariants = product.variants?.map { productMapper.toProductVariantEntity(it) }
                productVariants?.forEach { productVariant ->
                    saveProductVariant(productVariant)
                }
                val productImages = product.images?.map { productMapper.toProductImageEntity(it) }
                productImages?.forEach { productImage ->
                    saveProductImage(productImage)
                }
            }

        } catch (ex: Exception) {
            log.error("Error while save products.", ex)
        }
    }

    private fun saveProduct(productEntity: Product) {
        try {
            val isProductExist = productRepository.isProductExist(productEntity.id)
            if (!isProductExist) {
                productRepository.insertProduct(productEntity)
            } else {
                log.info("Product is already present in DB with id : {}", productEntity.id)
            }
        } catch (ex: Exception) {
            log.error("Error while save product", ex)
        }
    }

    private fun saveProductVariant(productVariant: ProductVariant) {
        try {
            val isExist = productVariantRepository.isProductVariantExist(productVariant.id)
            if (!isExist) {
                productVariantRepository.insertProductVariant(productVariant)
            } else {
                log.info("Product variant is already exists.")
            }
        } catch (ex: Exception) {
            log.error("Error while save product variant", ex)
        }
    }

    private fun saveProductImage(productImage: ProductImage) {
        try {
            val isExist = productImageRepository.isProductImageExist(productImage.id)
            if (!isExist) {
                productImageRepository.insertProductImage(productImage)
            } else {
                log.info("Product Image is already exists.")
            }
        } catch (ex: Exception) {
            log.error("Error while save product productImage", ex)
        }
    }

    fun getProducts(page: Int, size: Int): PageResponse<ProductResponse> {
        log.info("Invoke getProducts function")
        val products = productRepository.getProducts(page, size)
        val totalCount = productRepository.getTotalProducts()
        val totalPages = (totalCount + size - 1) / size
        val responseList = products.map { productMapper.toProductResponseDTO(it) }
        return PageResponse(
            totalElements = totalCount.toLong(),
            totalPages = totalPages,
            number = page,
            size = size,
            content = responseList
        )
    }

    fun addProduct(request: ProductRequestDTO) {
        log.info("Invoke addProduct function.")
        try {
            val product = productMapper.toProduct(request)
            saveProduct(product)
        } catch (ex: Exception) {
            log.error("Error while adding new product", ex)
        }
    }
}