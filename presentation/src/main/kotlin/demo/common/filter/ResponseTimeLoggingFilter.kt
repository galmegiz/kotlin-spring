package demo.common.filter

import demo.common.PresentationLog
import jakarta.servlet.Filter
import jakarta.servlet.FilterChain
import jakarta.servlet.ServletRequest
import jakarta.servlet.ServletResponse
import jakarta.servlet.http.HttpServletRequest
import org.springframework.core.annotation.Order
import org.springframework.stereotype.Component
import kotlin.time.measureTime

@Component
@Order(1)
class ResponseTimeLoggingFilter : Filter, PresentationLog {

    companion object {
        private const val LONG_RESPONSE_TIME = 1000L
    }
    override fun doFilter(request: ServletRequest, response: ServletResponse, chain: FilterChain) {
        val executionTIme = measureTime {
            chain.doFilter(request, response)
        }.inWholeMilliseconds

        if (executionTIme > LONG_RESPONSE_TIME) {
            log.warn("Slow Api call request : {}, executionTime : {}",
                (request as HttpServletRequest).requestURI,
                executionTIme)
        }
    }
}