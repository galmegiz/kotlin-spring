package com.example.service

import com.example.common.ErrorCode
import com.example.common.Log
import com.example.exception.ExternalApiException
import org.springframework.stereotype.Component

@Component
class ExternalClientA : ExternalClient, Log {
    companion object {
        private const val URL = "www.google.com"
    }
    override fun requestEx(request: String): String {
        log.info("request to $URL fail, param : {}", request)
        throw ExternalApiException(ErrorCode.UNKNOWN_SERVER_ERROR, URL)
    }
}