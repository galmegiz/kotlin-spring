package com.example.domain

import java.time.LocalDate
import java.time.LocalDateTime

class User(
    val id: Long,
    val email: String,
    val password: String?,
    val userName: String,
    var lastLoginAt: LocalDateTime,
    val deletedAt: LocalDate?) {

    fun isWithdrawal(): Boolean{
        return deletedAt != null
    }

    fun updateLastLoginTime() {
        this.lastLoginAt = LocalDateTime.now()
    }
}