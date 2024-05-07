package com.example.common.interceptor

import com.example.common.ErrorCode
import com.example.common.Log
import com.example.common.util.TokenUtil
import com.example.exception.AuthenticationException
import com.example.service.UserService
import jakarta.servlet.http.HttpServletRequest
import jakarta.servlet.http.HttpServletResponse
import org.springframework.http.HttpMethod
import org.springframework.stereotype.Component
import org.springframework.web.servlet.HandlerInterceptor

@Component
class TokenVerifyInterceptor(
    private val tokenUtil: TokenUtil,
    private val userService: UserService
) : HandlerInterceptor, Log {
    companion object {
        const val USER_KEY = "userProfile"
    }

    override fun preHandle(request: HttpServletRequest, response: HttpServletResponse, handler: Any): Boolean {
        val method = request.method
        if(method == HttpMethod.OPTIONS.name())
            return true

        try {
            val token: String = tokenUtil.getToken(request)
            val userEmail = tokenUtil.getEmail(token)
            val user = userService.findValidUserByEmail(userEmail)
            request.setAttribute(USER_KEY, user)
        } catch (e: IllegalStateException) {
            log.warn("user email is not valid. msg : {}", e.message)
            throw AuthenticationException(ErrorCode.USER_NOT_FOUND)
        } catch (e: AuthenticationException) {
            log.warn("user token is not valid. msg : {}", e.message)
            throw AuthenticationException(ErrorCode.BAD_CREDENTIALS_ERROR)
        } catch (e: Exception) {
            log.error("error occurs on verifying token. request uri : {}, token - {}, msg - {}",
                request.requestURI,
                request.getHeader("Authorization"),
                e.message)
            throw AuthenticationException(ErrorCode.BAD_CREDENTIALS_ERROR)
        }
        return true
    }
}