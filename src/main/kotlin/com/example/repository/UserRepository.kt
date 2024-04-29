package com.example.repository

import com.example.domain.User
import com.example.repository.mapper.UserMapper
import org.springframework.stereotype.Repository
import java.time.LocalDateTime

@Repository
class UserRepository(
    private val userMapper: UserMapper
) {

    fun saveUser(email: String, password: String, userName: String): Long {
        return userMapper.saveUser(email, password, userName)
    }

    fun findByUserId(userId: Long): User?{
        return userMapper.findByUserId(userId)
    }

    fun findByUserEmail(email: String): User?{
        return userMapper.findByUserEmail(email)
    }

    fun updateUser(
        userId: Long,
        email: String? = null,
        password: String? = null,
        userName: String? = null,
        lastLoginAt: LocalDateTime? = null
    ): Boolean {
        return userMapper.updateUser(
            userId,
            email,
            password,
            userName,
            lastLoginAt
        )
    }
}