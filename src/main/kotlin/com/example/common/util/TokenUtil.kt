package com.example.common.util

import com.example.common.ErrorCode
import com.example.exception.AuthenticationException
import jakarta.servlet.http.HttpServletRequest
import org.springframework.stereotype.Component
import org.springframework.util.StringUtils

@Component
class TokenUtil(
    val jwtUtil: JwtUtil
) {
    companion object {
        private const val AUTHORIZATION_HEADER = "Authorization"
        private const val BEARER_PREFIX = "Bearer "
    }

    private fun getHttpRequestToken(request: HttpServletRequest): String {
        val bearerToken = request.getHeader(AUTHORIZATION_HEADER)
        return when {
            StringUtils.hasText(bearerToken) && bearerToken.startsWith(BEARER_PREFIX) -> bearerToken.substring(
                BEARER_PREFIX.length
            )
            else -> throw AuthenticationException(ErrorCode.BAD_CREDENTIALS_ERROR)
        }
    }

    fun getToken(request: HttpServletRequest): String {
        val token: String = getHttpRequestToken(request)

        if (!jwtUtil.verifyToken(token)) {
            throw AuthenticationException(ErrorCode.BAD_CREDENTIALS_ERROR)
        }
        return token
    }

    fun getId(token: String): Long = jwtUtil.getClaim(token, "userId")?.toLong()
        ?: throw AuthenticationException(ErrorCode.BAD_CREDENTIALS_ERROR)

    fun getEmail(token: String): String = jwtUtil.getClaim(token, "email")
        ?: throw AuthenticationException(ErrorCode.BAD_CREDENTIALS_ERROR)
}