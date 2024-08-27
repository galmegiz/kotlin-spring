package demo.common.interceptor

import demo.common.Log
import demo.common.ErrorCode
import demo.auth.service.UserService
import demo.common.util.TokenUtil
import io.jsonwebtoken.ExpiredJwtException
import io.jsonwebtoken.MalformedJwtException
import io.jsonwebtoken.UnsupportedJwtException
import jakarta.servlet.http.HttpServletRequest
import jakarta.servlet.http.HttpServletResponse
import org.springframework.http.HttpMethod
import org.springframework.stereotype.Component
import org.springframework.web.servlet.HandlerInterceptor
import java.lang.IllegalArgumentException
import demo.common.exception.SecurityException

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
            tokenUtil.verifyToken(token)
            val userEmail = tokenUtil.getEmail(token)
            val user = userService.findValidUserByEmail(userEmail)
            request.setAttribute(USER_KEY, user)
        } catch (e: Exception) {
            when (e) {
                is MalformedJwtException,
                is UnsupportedJwtException,
                is IllegalArgumentException -> {
                    log.debug("token is expired")
                    throw SecurityException(ErrorCode.BAD_CREDENTIALS_ERROR)
                }
                is ExpiredJwtException -> {
                    log.debug("token is expired")
                    throw SecurityException(ErrorCode.TOKEN_EXPIRED)
                }
                is SecurityException -> {
                    log.warn("user cannot access cause : {}", e.errorCode)
                    throw e
                }
                else -> {
                    log.error("unexpected error occurs on verifying token. request uri : {}, token - {}, msg - {}",
                    request.requestURI,
                    request.getHeader("Authorization"),
                    e.message, e)
                    throw demo.common.exception.SecurityException(ErrorCode.BAD_CREDENTIALS_ERROR)
                }

            }
        }
        return true
    }
}