package com.example.common

data class CommonApiResponse<T>(
    val success: Boolean = false,
    val data: T? = null,
    val errorCode: ErrorCode? = null,
    val message: String? = null
)
