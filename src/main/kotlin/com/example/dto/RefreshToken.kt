package com.example.dto

class RefreshToken(
    val email: String,
    var value: String
) {
    fun updateValue(token: String): RefreshToken {
        this.value = token
        return this
    }
}