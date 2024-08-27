package demo.common.external

import demo.common.ErrorCode
import demo.common.Log
import demo.common.exception.ExternalApiException
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

    override fun request(request: String): String {
        return "request to $URL (param : $request) success"
    }

    override fun timeout(param: String): String {
        Thread.sleep(3000)
        return param
    }
}