package com.example.common

data class CommonApiResponse<T>(
    val success: Boolean = false,
    val data: T? = null,
    val errorCode: String? = null,
    val message: String? = null
)
