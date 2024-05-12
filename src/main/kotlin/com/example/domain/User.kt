package com.example.domain

import java.time.LocalDate
import java.time.LocalDateTime

class User(
    val id: Long?,
    val email: String,
    val password: String?,
    val userName: String,
    var lastLoginAt: LocalDateTime? = null,
    val deletedAt: LocalDate?) {

    companion object{
        fun of(
            email: String,
            password: String,
            userName: String
        ): User {
            return User(
                id = null,
                email = email,
                password = password,
                userName = userName,
                lastLoginAt = null,
                deletedAt = null
            )
        }
    }

    fun isWithdrawal(): Boolean{
        return deletedAt != null
    }

    fun updateLastLoginTime() {
        this.lastLoginAt = LocalDateTime.now()
    }
}