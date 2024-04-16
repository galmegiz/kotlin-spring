package com.example.common

import org.slf4j.event.Level
import org.springframework.boot.logging.LogLevel
import org.springframework.http.HttpStatus

enum class ErrorCode(
    val code: String,
    val message: String,
    val httpStatusCode: HttpStatus,
    val logLevel: Level = Level.WARN
) {
    UNKNOWN_SERVER_ERROR("F99999", "알 수 없는 서버 에러입니다", HttpStatus.INTERNAL_SERVER_ERROR),

    //employees
    EMPLOYEE_NOT_FOUND("A00001", "종사자를 찾을 수 없습니다.", HttpStatus.NOT_FOUND, logLevel = Level.INFO)
}