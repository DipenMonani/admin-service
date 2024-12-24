package com.admin.exceptions

import com.admin.utils.Response
import feign.FeignException.InternalServerError
import io.github.wimdeblauwe.htmx.spring.boot.mvc.HtmxRequest
import io.github.wimdeblauwe.htmx.spring.boot.mvc.HtmxResponse
import org.slf4j.LoggerFactory
import org.springframework.web.bind.MethodArgumentNotValidException
import org.springframework.web.bind.annotation.ControllerAdvice
import org.springframework.web.bind.annotation.ExceptionHandler
import org.springframework.web.servlet.ModelAndView

@ControllerAdvice
class MvcExceptionHandler {
    private val log = LoggerFactory.getLogger(javaClass)

    @ExceptionHandler(RuntimeException::class)
    fun handleException(e: RuntimeException, htmxRequest: HtmxRequest): ModelAndView {
        log.error("Error in request ", e)

        return Response.errorMessage(e.message ?: "Unknown error")
    }

    @ExceptionHandler(MethodArgumentNotValidException::class)
    fun handleException(e: MethodArgumentNotValidException): ModelAndView {
        log.warn("Error in request ", e)
        val message = e.bindingResult.fieldErrors.map { it.field + ": " + it.defaultMessage }
        return Response.errorMessage(message)
    }

    @ExceptionHandler(InternalServerError::class)
    fun handleException(e: InternalServerError, htmxRequest: HtmxRequest): HtmxResponse? {
        log.error("Error in request ", e)

        return htmxRequest.currentUrl?.let { Response.errorRedirect(it, "Network Error , Please try again later") }
    }

}