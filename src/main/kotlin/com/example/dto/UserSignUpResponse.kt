package com.example.dto

data class UserSignUpResponse(
    val id: Long,
    val email: String,
    val userName: String,
    val loginInfo: Login
)
