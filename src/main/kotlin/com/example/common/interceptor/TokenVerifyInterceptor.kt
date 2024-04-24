package com.example.common.interceptor

import com.example.common.Log
import com.example.common.util.TokenUtil
import jakarta.servlet.http.HttpServletRequest
import jakarta.servlet.http.HttpServletResponse
import org.springframework.http.HttpMethod
import org.springframework.stereotype.Component
import org.springframework.web.servlet.HandlerInterceptor

@Component
class TokenVerifyInterceptor(
    private val tokenUtil: TokenUtil
) : HandlerInterceptor, Log {
    override fun preHandle(request: HttpServletRequest, response: HttpServletResponse, handler: Any): Boolean {
        val method = request.method
        if(method == HttpMethod.OPTIONS.name())
            return true

        /*try{
            val token: String = tokenUtil.getToken(request)

        }*/
        return true
    }
}