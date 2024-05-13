package com.example.common.util

import com.example.common.Log
import com.example.dto.Token
import io.jsonwebtoken.Claims
import io.jsonwebtoken.ExpiredJwtException
import io.jsonwebtoken.Jwts
import io.jsonwebtoken.MalformedJwtException
import io.jsonwebtoken.SignatureAlgorithm
import io.jsonwebtoken.UnsupportedJwtException
import io.jsonwebtoken.io.Decoders
import io.jsonwebtoken.security.Keys
import org.springframework.beans.factory.annotation.Value
import org.springframework.stereotype.Component
import java.security.Key
import java.util.*

@Component
class JwtUtil(
    @Value("\${jwt.secret}")
    val secretKey: String,
    @Value("\${jwt.access-token-expire-time}")
    val accessTokenExpireTime: Long,
    @Value("\${jwt.refresh-token-expire-time}")
    val refreshTokenExpireTime: Long
) : Log {
    companion object{
        private const val GRANT_TYPE = "Bearer"
    }

    private val key: Key = Keys.hmacShaKeyFor(Decoders.BASE64.decode(secretKey))

    fun generateToken(userType: String, id: Long, email: String): Token {
        val now = Date().time
        val accessTokenExpireIn = Date(now + accessTokenExpireTime)
        val accessToken: String = Jwts.builder()
            .setSubject(email)
            .claim("userId", id)
            .claim("email", email)
            .setExpiration(accessTokenExpireIn)
            .signWith(key, SignatureAlgorithm.HS512)
            .compact()

        val refreshTokenExpiresIn = Date(now + refreshTokenExpireTime)
        val refreshToken: String = Jwts.builder()
            .setExpiration(refreshTokenExpiresIn)
            .claim("userId", id)
            .claim("email", email)
            .signWith(key, SignatureAlgorithm.HS512)
            .compact()

        return Token(
            grantType = GRANT_TYPE,
            accessToken = accessToken,
            refreshToken = refreshToken,
            accessTokenExpiresIn = accessTokenExpireIn,
            refreshTokenExpiresIn = refreshTokenExpiresIn
        )
    }

    fun verifyToken(token: String): Boolean {
        try {
            Jwts.parserBuilder().setSigningKey(key).build().parseClaimsJws(token)
            return true
        } catch (e: SecurityException) {
            log.info("잘못된 JWT 서명입니다.")
        } catch (e: MalformedJwtException) {
            log.info("잘못된 JWT 서명입니다.")
        } catch (e: ExpiredJwtException) {
            log.info("만료된 JWT 토큰입니다.")
        } catch (e: UnsupportedJwtException) {
            log.info("지원되지 않는 JWT 토큰입니다.")
        } catch (e: IllegalArgumentException) {
            log.info("JWT 토큰이 잘못되었습니다.")
        }
        return false
    }

    fun getClaim(token: String, key: String): String? = getAllClaims(token)[key].toString()

    private fun getAllClaims(token: String): Claims {
        return try {
            Jwts.parserBuilder().setSigningKey(key).build().parseClaimsJws(token).body
        } catch (e: ExpiredJwtException) {
            e.claims
        }
    }
}