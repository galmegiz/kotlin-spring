package demo.controller

import domain.common.external.ExternalCBApieService
import domain.common.external.ExternalRetryApiService
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController

@RestController
@RequestMapping("/external")
class ExternalApiController(
    private val externalRetryApiService: ExternalRetryApiService,
    private val externalCBApieService: ExternalCBApieService,
) {

    @GetMapping("/retry")
    fun retryTest(param: String): String {
        return externalRetryApiService.requestApi(param)
    }

    @GetMapping("/retry2")
    fun retryTest2(param: String): String {
        return externalRetryApiService.requestApi2(param)
    }

    @GetMapping("/cb")
    fun test2(param: String): String {
        return externalCBApieService.requestApi(param)
    }
}