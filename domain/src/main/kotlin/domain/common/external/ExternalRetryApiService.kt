package domain.common.external

import domain.common.Log
import domain.common.exception.ExternalApiException
import io.github.resilience4j.retry.annotation.Retry
import org.springframework.stereotype.Service


@Service
class ExternalRetryApiService(
    private val externalClients: ExternalClient
) : Log {
    companion object {
        const val DEFAULT_RETRY_CONFIG = "defaultConfig"
        const val ADVANCED_RETRY_CONFIG = "advancedConfig"
    }

    @Retry(name = DEFAULT_RETRY_CONFIG, fallbackMethod = "fallback")
    fun requestApi(request: String): String {
        return externalClients.requestEx(request)
    }

    @Retry(name = ADVANCED_RETRY_CONFIG, fallbackMethod = "fallback")
    fun requestApi2(request: String): String {
        return externalClients.requestEx(request)
    }

    private fun fallback(request: String, ex: ExternalApiException): String{
        log.info("request : {}, targetUrl : {}", request, ex.serviceUrl)
        return "api request to ${ex.serviceUrl} fail"
    }
}