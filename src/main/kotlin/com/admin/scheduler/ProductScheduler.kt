package com.admin.scheduler

import com.admin.clients.ProductClient
import com.admin.constants.MDC_TRACE_ID
import com.admin.dto.ResponseDTO
import com.admin.service.ProductService
import com.fasterxml.jackson.databind.DeserializationFeature
import com.fasterxml.jackson.databind.ObjectMapper
import com.fasterxml.jackson.module.kotlin.jacksonObjectMapper
import org.slf4j.LoggerFactory
import org.slf4j.MDC
import org.springframework.scheduling.annotation.Scheduled
import org.springframework.stereotype.Component
import java.util.*

@Component
class ProductScheduler(
    private val productClient: ProductClient,
    private val productService: ProductService
) {

    private val log = LoggerFactory.getLogger(javaClass)

    val objectMapper: ObjectMapper =
        jacksonObjectMapper().configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false)

    @Scheduled(fixedRate = 5 * 60 * 1000, initialDelay = 0)
    fun init() {
        try {
            MDC.put(MDC_TRACE_ID, UUID.randomUUID().toString())
            log.info("Invoke init scheduler.")
            val productsJson = productClient.getProducts()

            val responseDTO = objectMapper.readValue(productsJson, ResponseDTO::class.java)
            productService.saveProducts(responseDTO?.products)
        } catch (ex: Exception) {
            log.error("Error get products.", ex)
        } finally {
            MDC.remove(MDC_TRACE_ID)
        }
    }
}