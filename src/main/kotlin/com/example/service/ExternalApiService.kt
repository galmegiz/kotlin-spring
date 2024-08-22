package com.example.service

import com.example.common.ErrorCode
import com.example.common.Log
import com.example.exception.ExternalApiException
import com.example.exception.SecurityException
import io.github.resilience4j.retry.annotation.Retry
import org.springframework.stereotype.Service


@Service
class ExternalApiService(
    private val externalClients: ExternalClient
) : Log{
    companion object {
        const val DEFAULT_RETRY_CONFIG = "defaultConfig"
    }

    @Retry(name = DEFAULT_RETRY_CONFIG, fallbackMethod = "fallback")
    fun requestApi(request: String): String {
        return externalClients.requestEx(request)
    }

    private fun fallback(request: String, ex: ExternalApiException): String{
        log.info("request : {}, targetUrl : {}", request, ex.serviceUrl)
        return "api request to ${ex.serviceUrl} fail"
    }
}