package com.example.repository

import com.example.dto.RefreshToken

interface RefreshTokenRepository {
    fun save(refreshToken: RefreshToken)
    fun findByUserEmail(email: String): RefreshToken?
    fun updateToken(updatedToken: RefreshToken)
}