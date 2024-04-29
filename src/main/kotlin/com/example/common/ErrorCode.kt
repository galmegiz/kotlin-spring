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

    BAD_CREDENTIALS_ERROR("E01100", "로그인에 실패하였습니다.", HttpStatus.UNAUTHORIZED),
    USER_NOT_FOUND("E01101", "유효한 유저가 존재하지 않습니다.", HttpStatus.NOT_FOUND),

    //employees
    EMPLOYEE_NOT_FOUND("E01200", "종사자를 찾을 수 없습니다.", HttpStatus.NOT_FOUND, logLevel = Level.INFO),

}