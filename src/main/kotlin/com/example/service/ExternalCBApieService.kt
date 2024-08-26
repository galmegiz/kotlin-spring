package com.example.service

import com.example.common.Log
import com.example.exception.ExternalApiException
import io.github.resilience4j.circuitbreaker.CallNotPermittedException
import io.github.resilience4j.circuitbreaker.ResultRecordedAsFailureException
import io.github.resilience4j.circuitbreaker.annotation.CircuitBreaker
import org.springframework.stereotype.Service

@Service
class ExternalCBApieService(
    private val externalClient: ExternalClient
) : Log {
    companion object {
        const val DEFAULT_CB_CONFIG = "simpleCircuitBreakerConfig"
    }

    @CircuitBreaker(name = DEFAULT_CB_CONFIG, fallbackMethod = "fallBack")
    fun requestApi(param: String): String {
        return when (param) {
            "OPEN" -> externalClient.requestEx(param)
            "CLOSE" -> externalClient.requestEx(param)
            "TIME_OUT" -> externalClient.timeout(param)
            else -> throw IllegalArgumentException()
        }
    }

    private fun fallBack(param: String, ex: ExternalApiException): String {
        log.info("api response error, state is CLOSE -> OPEN")
        return ex.message ?: ""
    }

    private fun fallBack(param: String, ex: CallNotPermittedException): String {
        log.info("CB state is open, api is not permitted")
        return ex.message ?: ""
    }
}