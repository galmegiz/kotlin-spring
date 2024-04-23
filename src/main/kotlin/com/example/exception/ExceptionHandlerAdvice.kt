package com.example.exception

import com.example.common.ErrorCode
import com.example.common.Log
import com.example.common.CommonApiResponse
import com.example.domain.Employee
import jakarta.servlet.http.HttpServletRequest
import org.slf4j.event.Level
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.ExceptionHandler
import org.springframework.web.bind.annotation.RestControllerAdvice
import org.springframework.web.servlet.resource.NoResourceFoundException

@RestControllerAdvice
class ExceptionHandlerAdvice : Log {

    @ExceptionHandler(Exception::class)
    fun unExpectedExceptionHandler(e: Exception): ResponseEntity<CommonApiResponse<Any?>>{
        log.error("UNEXPECTED_ERROR!! errorType : {}, message: {}", e::class, e.message)
        log.error("error trace log : ", e)
        return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(
            CommonApiResponse(
                success = false,
                errorCode = ErrorCode.UNKNOWN_SERVER_ERROR.code,
                message = ErrorCode.UNKNOWN_SERVER_ERROR.message
            )
        )
    }

    @ExceptionHandler(IllegalStateException::class)
    fun checkException(e: Exception): ResponseEntity<CommonApiResponse<Any?>>{
        log.error("IllegalStateException!! message: {}", e.message)

        return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(
            CommonApiResponse(
                success = false,
                errorCode = ErrorCode.UNKNOWN_SERVER_ERROR.code,
                message = ErrorCode.UNKNOWN_SERVER_ERROR.message
            )
        )
    }

/*    @ExceptionHandler(NoResourceFoundException::class)
    fun noResourceFoundException(e: NoResourceFoundException): ResponseEntity<CommonApiResponse<Any?>>{
        log.error("resource not found!! message: {}", e.message)
//        throw ResourceNotFoundException(ErrorCode.EMPLOYEE_NOT_FOUND, Employee::class)
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(
            CommonApiResponse(
                success = false,
                errorCode = ErrorCode.EMPLOYEE_NOT_FOUND.code,
                message = ErrorCode.EMPLOYEE_NOT_FOUND.message
            )
        )
    }*/

    @ExceptionHandler(BaseServiceException::class)
    fun baseExceptionHandler(e: BaseServiceException, request: HttpServletRequest): ResponseEntity<CommonApiResponse<Any?>>{
        val errorMessage = e.message.orEmpty()
        val errorCode = e.errorCode

        when (errorCode.logLevel) {
            Level.TRACE -> log.trace("[ServiceException] request method: ${request.method}, request uri: ${request.requestURI}, error message: $errorMessage")
            Level.DEBUG -> log.debug("[ServiceException] request method: ${request.method}, request uri: ${request.requestURI}, error message: $errorMessage")
            Level.INFO -> log.info("[ServiceException] request method: ${request.method}, request uri: ${request.requestURI}, error message: $errorMessage")
            Level.WARN -> log.warn(
                "[ServiceException] request method: ${request.method}, request uri: ${request.requestURI}, error message: $errorMessage",
                e
            )
            Level.ERROR -> log.error(
                "[ServiceException] request method: ${request.method}, request uri: ${request.requestURI}, error message: $errorMessage",
                e
            )
        }

        return ResponseEntity.status(HttpStatus.OK).body(
            CommonApiResponse(
                success = false,
                errorCode = errorCode.code,
                message = errorMessage
            )
        )
    }
}