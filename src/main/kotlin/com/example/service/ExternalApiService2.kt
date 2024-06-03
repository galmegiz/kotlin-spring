package com.example.service

import com.example.common.Log
import io.github.resilience4j.circuitbreaker.annotation.CircuitBreaker
import org.springframework.stereotype.Service
import java.lang.IllegalStateException

@Service
class ExternalApiService2 : Log {
    companion object {
        const val DEFAULT_CB_CONFIG = "simpleCircuitBreakerConfig"
    }

    @CircuitBreaker(name = DEFAULT_CB_CONFIG, fallbackMethod = "fallBack")
    fun test(param: String): String {
        return callAnotherService(param)
    }

    private fun fallBack(param: String, ex: Exception): String {
        log.info("cb falback")
        return "fallback $param"
    }

    private fun callAnotherService(param: String): String {
        when (param) {
            "a" -> throw IllegalArgumentException()
            "b" -> throw IllegalStateException()
            else -> return param
        }
    }

}