package com.example.repository

import com.example.dto.RefreshToken
import com.example.repository.mapper.RefreshTokenMapper
import org.springframework.stereotype.Repository

@Repository
class DbRefreshTokenRepository(
    private val refreshTokenMapper: RefreshTokenMapper
) : RefreshTokenRepository {
    override fun save(refreshToken: RefreshToken) {
        check(refreshTokenMapper.saveRefreshToken(refreshToken) > 0){"save refresh token fail"}
    }

    override fun findByUserEmail(email: String): RefreshToken? {
        return refreshTokenMapper.findByEmail(email)
    }

    override fun updateToken(updatedToken: RefreshToken) {
        check(refreshTokenMapper.updateRefreshToken(updatedToken) > 0){"update refresh token fail"}
    }
}