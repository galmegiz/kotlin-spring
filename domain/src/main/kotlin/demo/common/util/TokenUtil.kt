package demo.common.util

import demo.common.ErrorCode
import demo.auth.dto.Token
import io.jsonwebtoken.ExpiredJwtException
import io.jsonwebtoken.MalformedJwtException
import io.jsonwebtoken.UnsupportedJwtException
import jakarta.servlet.http.HttpServletRequest
import org.springframework.stereotype.Component
import org.springframework.util.StringUtils
import demo.common.exception.SecurityException

@Component
class TokenUtil(
    private val jwtUtil: JwtUtil
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
            else -> throw SecurityException(ErrorCode.BAD_CREDENTIALS_ERROR)
        }
    }

    fun generateToken(userType: String, id: Long, email: String): Token {
        return jwtUtil.generateToken("temp", id, email)
    }

    fun getToken(request: HttpServletRequest): String = getHttpRequestToken(request)

    @Throws(SecurityException::class, MalformedJwtException::class, ExpiredJwtException::class, UnsupportedJwtException::class, IllegalStateException::class)
    fun verifyToken(token: String): Boolean = jwtUtil.verifyToken(token)

    fun getId(token: String): Long = jwtUtil.getClaim(token, "userId")?.toLong()
        ?: throw SecurityException(ErrorCode.BAD_CREDENTIALS_ERROR)

    fun getEmail(token: String): String = jwtUtil.getClaim(token, "email")
        ?: throw SecurityException(ErrorCode.BAD_CREDENTIALS_ERROR)
}