package com.admin.exceptions

import org.slf4j.LoggerFactory
import org.springframework.core.annotation.Order
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.ControllerAdvice
import org.springframework.web.bind.annotation.ExceptionHandler
import org.springframework.web.client.HttpServerErrorException.InternalServerError
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler

@ControllerAdvice
@Order
class RestExceptionHandler : ResponseEntityExceptionHandler() {

    private val log = LoggerFactory.getLogger(javaClass)

    @ExceptionHandler(RequestInvalidException::class)
    fun handleRequestInvalidException(ex: RequestInvalidException): ResponseEntity<ApiResponse> {
        log.error("request not valid exception", ex)
        val apiError = ApiResponse(HttpStatus.BAD_REQUEST, "Request Invalid", ex.message)
        return buildResponseEntity(apiError)
    }

    @ExceptionHandler(FeignClientException::class)
    fun handleFeignClientException(ex: FeignClientException): ResponseEntity<ApiResponse> {
        log.error("FeignClient exception", ex)
        val apiError = ApiResponse(HttpStatus.INTERNAL_SERVER_ERROR, "Internal Server Error", ex.message)
        return buildResponseEntity(apiError)
    }

    @ExceptionHandler(InternalServerError::class)
    fun handleInternalServerErrorException(ex: InternalServerError): ResponseEntity<ApiResponse> {
        log.error("Internal server exception", ex)
        val apiError = ApiResponse(HttpStatus.INTERNAL_SERVER_ERROR, "Internal Server Error", ex.message)
        return buildResponseEntity(apiError)
    }

    @ExceptionHandler(Exception::class)
    fun handleGlobalException(ex: Exception): ResponseEntity<ApiResponse> {
        log.error("Internal server exception", ex)
        val apiError = ApiResponse(HttpStatus.INTERNAL_SERVER_ERROR, "General Error", ex.message)
        return buildResponseEntity(apiError)
    }

    private fun buildResponseEntity(apiError: ApiResponse): ResponseEntity<ApiResponse> {
        return ResponseEntity(apiError, apiError.getCode())
    }
}
