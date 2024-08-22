package com.example.controller

import com.example.service.ExternalApiService
import com.example.service.ExternalApiService2
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController

@RestController
@RequestMapping("/external")
class ExternalApiController(
    private val externalApiService: ExternalApiService,
    private val externalApiService2: ExternalApiService2,
) {

    @GetMapping("/retry")
    fun retryTest(param: String): String {
        return externalApiService.requestApi("Aaa")
    }

    @GetMapping("/cb")
    fun test2(param: String): String {
        return externalApiService2.test(param)
    }
}