package com.example.controller

import com.example.service.ExternalApiService
import org.springframework.stereotype.Controller
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController

@RestController
@RequestMapping("/test")
class ResilienceController(
    private val externalApiService: ExternalApiService
) {

    @GetMapping("")
    fun test(): String {
        return externalApiService.requestApi("Aaa")
    }
}