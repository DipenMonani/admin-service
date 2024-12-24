package com.admin.utils

import io.github.wimdeblauwe.htmx.spring.boot.mvc.HtmxLocation
import io.github.wimdeblauwe.htmx.spring.boot.mvc.HtmxRequest
import io.github.wimdeblauwe.htmx.spring.boot.mvc.HtmxResponse
import org.springframework.http.HttpStatus
import org.springframework.web.servlet.ModelAndView

// Maybe do with builder style
// Add javadoc
// needs much simplification
object Response {
	fun content(
		htmxRequest: HtmxRequest,
		view: String,
		pushUrl: String? = null
	): HtmxResponse {
		val response = HtmxResponse.builder().view(if (htmxRequest.isHtmxRequest) "$view :: content" else view)
		pushUrl?.let { response.pushUrl(it) }
		return response.build()
	}

	fun fragment(htmxRequest: HtmxRequest, view: String, fragment: String): HtmxResponse {
		return HtmxResponse.builder()
			.view(if (htmxRequest.isHtmxRequest) "$view :: $fragment" else view)
			.build()
	}

	fun fragment(
		htmxRequest: HtmxRequest,
		view: String,
		fragment: String,
		message: String,
		bannerName: String = "banner_success"
	): HtmxResponse {
		return HtmxResponse.builder()
			.view(if (htmxRequest.isHtmxRequest) "$view :: $fragment" else view)
			.view(ModelAndView("page-banner", mapOf(bannerName to message)))
			.build()
	}

	fun redirect(uri: String, message: String? = null): HtmxResponse {
		return HtmxResponse.builder()
			.redirect(uri)
			.view(ModelAndView("page-banner", mapOf("banner_success" to message)))
			.build()
	}

	fun errorRedirect(uri: String, message: String? = null): HtmxResponse {
		val htmxLocation = HtmxLocation(uri)
		htmxLocation.target = "#content"
		return HtmxResponse.builder()
//			.location(htmxLocation)
			// location and view doesn't work together!!
			.view(ModelAndView("page-banner", mapOf("banner_error" to message)))
			.build()
	}

	fun errorMessage(message: String): ModelAndView {
		return errorMessage(listOf(message))
	}

	fun errorMessage(message: List<String>): ModelAndView {
		val response = ModelAndView("page-banner", mapOf("banner_error" to message))
		response.status = HttpStatus.INTERNAL_SERVER_ERROR
		return response
	}
}
