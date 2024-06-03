package com.example.service

import com.example.common.ErrorCode
import com.example.common.Log
import com.example.exception.SecurityException
import io.github.resilience4j.retry.annotation.Retry
import org.springframework.stereotype.Service


@Service
class ExternalApiService : Log{
    companion object {
        const val DEFAULT_RETRY_CONFIG = "retryConfig"
    }

    @Retry(name = DEFAULT_RETRY_CONFIG, fallbackMethod = "fallback")
    fun requestApi(param: String): String {
        return callAnotherApi(param)
    }

    private fun callAnotherApi(param: String): String {
        throw SecurityException(ErrorCode.BAD_CREDENTIALS_ERROR)
    }

    private fun fallback(param: String, ex: Exception): String{
        log.info("fallback")
        return param
    }

}