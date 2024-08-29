package domain.auth.repository

import domain.auth.dto.RefreshToken

interface RefreshTokenRepository {
    fun save(refreshToken: RefreshToken)
    fun findByUserEmail(email: String): RefreshToken?
    fun updateToken(updatedToken: RefreshToken)
}