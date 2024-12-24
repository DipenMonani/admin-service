package com.admin.config

import jakarta.servlet.http.HttpServletRequest
import jakarta.servlet.http.HttpServletResponse
import org.slf4j.MDC
import org.springframework.stereotype.Component
import org.springframework.web.servlet.HandlerInterceptor
import java.util.*


@Component
class LogInterceptor : HandlerInterceptor {
    @Throws(Exception::class)
    override fun preHandle(request: HttpServletRequest, response: HttpServletResponse, handler: Any): Boolean {
        var tracingId = request.getHeader("eventTraceId")
        if (tracingId.isNullOrBlank()) {
            tracingId = UUID.randomUUID().toString()
        }
        MDC.put("eventTraceId", tracingId)

        return super.preHandle(request, response, handler)
    }

    @Throws(Exception::class)
    override fun afterCompletion(
        request: HttpServletRequest,
        response: HttpServletResponse,
        handler: Any,
        ex: Exception?
    ) {
        MDC.remove("eventTraceId")
        super.afterCompletion(request, response, handler, ex)
    }
}