package com.example.service

import com.example.common.ErrorCode
import com.example.common.Log
import com.example.common.util.TokenUtil
import com.example.domain.User
import com.example.dto.RefreshToken
import com.example.dto.Token
import com.example.exception.AuthenticationException
import com.example.repository.RefreshTokenRepository
import com.example.repository.UserRepository
import org.springframework.security.crypto.password.PasswordEncoder
import org.springframework.stereotype.Service
import org.springframework.transaction.annotation.Transactional

@Transactional
@Service
class AuthService(
    private val refreshTokenRepository: RefreshTokenRepository,
    private val userRepository: UserRepository,
    private val passwordEncoder: PasswordEncoder,
    private val tokenUtil : TokenUtil
) : Log {

    fun generateToken(user: User): Token {
        requireNotNull(user.id)
        val token = tokenUtil.generateToken("test", user.id, user.email)
        val refreshToken = RefreshToken(user.email, passwordEncoder.encode(token.refreshToken))
        refreshTokenRepository.save(refreshToken)
        return token
    }

    fun updateToken(tokenRefreshRequest: RefreshToken): Token {
        val email = tokenUtil.getEmail(tokenRefreshRequest.tokenValue)
        if(tokenRefreshRequest.email != email )
            throw AuthenticationException(ErrorCode.BAD_CREDENTIALS_ERROR)
        val user = userRepository.findByUserEmail(email) ?: throw NoSuchElementException()

        val oldRefreshToken = refreshTokenRepository.findByUserEmail(user.email)

        if (oldRefreshToken == null ||
            !passwordEncoder.matches(tokenRefreshRequest.tokenValue, oldRefreshToken.tokenValue)) {
            throw AuthenticationException(ErrorCode.BAD_CREDENTIALS_ERROR)
        }

        val updatedToken = tokenUtil.generateToken("test", user.id!!, user.email)
        val newRefreshToken = RefreshToken(email = user.email, tokenValue = passwordEncoder.encode(updatedToken.refreshToken))
        refreshTokenRepository.updateToken(newRefreshToken)
        return updatedToken
    }
}