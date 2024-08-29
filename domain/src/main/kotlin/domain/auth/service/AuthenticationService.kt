package domain.auth.service

import domain.common.ErrorCode
import domain.auth.dto.RefreshToken
import domain.auth.dto.Token
import domain.auth.entity.User
import domain.auth.repository.RefreshTokenRepository
import domain.auth.repository.UserRepository
import domain.common.Log
import domain.common.util.TokenUtil
import org.springframework.security.crypto.password.PasswordEncoder
import org.springframework.stereotype.Service
import org.springframework.transaction.annotation.Transactional
import domain.common.exception.SecurityException

@Transactional
@Service
class AuthenticationService(
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
            throw SecurityException(ErrorCode.BAD_CREDENTIALS_ERROR)
        val user = userRepository.findByUserEmail(email) ?: throw NoSuchElementException()

        val oldRefreshToken = refreshTokenRepository.findByUserEmail(user.email)

        if (oldRefreshToken == null ||
            !passwordEncoder.matches(tokenRefreshRequest.tokenValue, oldRefreshToken.tokenValue)) {
            throw SecurityException(ErrorCode.BAD_CREDENTIALS_ERROR)
        }

        val updatedToken = tokenUtil.generateToken("test", user.id!!, user.email)
        val newRefreshToken = RefreshToken(email = user.email, tokenValue = passwordEncoder.encode(updatedToken.refreshToken))
        refreshTokenRepository.updateToken(newRefreshToken)
        return updatedToken
    }
}