package demo.exception

import domain.common.ErrorCode
import domain.common.exception.BaseServiceException
import jakarta.servlet.http.HttpServletRequest
import org.slf4j.event.Level
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.http.converter.HttpMessageNotReadableException
import org.springframework.web.bind.MethodArgumentNotValidException
import org.springframework.web.bind.annotation.ExceptionHandler
import org.springframework.web.bind.annotation.RestControllerAdvice
import org.springframework.web.method.annotation.MethodArgumentTypeMismatchException
import org.springframework.web.servlet.resource.NoResourceFoundException
import demo.common.CommonApiResponse
import demo.common.Log
import java.lang.StringBuilder

@RestControllerAdvice
class ExceptionHandlerAdvice : Log {

    @ExceptionHandler(Exception::class)
    fun unExpectedExceptionHandler(e: Exception, httpRequest: HttpServletRequest): ResponseEntity<CommonApiResponse<Any?>>{
        log.error("request uri : {}", httpRequest.requestURI)
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

    @ExceptionHandler(NoResourceFoundException::class)
    fun noResourceFoundExceptionHandler(e: NoResourceFoundException, httpRequest: HttpServletRequest): ResponseEntity<CommonApiResponse<Any?>>{
        log.error("request uri : {}", httpRequest.requestURI)
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(
            CommonApiResponse(
                success = false,
                errorCode = ErrorCode.USER_NOT_FOUND.code,
                message = ErrorCode.USER_NOT_FOUND.message
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

    @ExceptionHandler(MethodArgumentNotValidException::class)
    fun argumentNotValidHandler(e: MethodArgumentNotValidException): ResponseEntity<CommonApiResponse<Any?>>{
        log.info("Invalid user input: {}", e.bindingResult.toString())

        // TODO 필요하다면 개선
        val sb = StringBuilder()
        for (fieldError in e.bindingResult.fieldErrors) {
            sb.append("field : ${fieldError.field}, rejectedValue : ${fieldError.rejectedValue}, msg : ${fieldError.defaultMessage}")
            sb.append("\n")
        }
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(
            CommonApiResponse(
                success = false,
                errorCode = ErrorCode.INVALID_USER_INPUT.code,
                message = sb.toString()
            )
        )
    }

    // HttpMessageConverter가 json -> object 변환 중 에러 ex) enum에 없는 값을 enum에 입력
    // AbstractNamedValueMethodArgumentResolver가 Stirng -> Int과 같이 타입 변환 실패
    @ExceptionHandler(value = [HttpMessageNotReadableException::class, MethodArgumentTypeMismatchException::class])
    fun httpMessageConvertingExceptionHandler(e: Exception, httpRequest: HttpServletRequest): ResponseEntity<CommonApiResponse<Any?>>{
        log.info("Http Request Parsing Error. request uri : {}, message : {}", httpRequest.requestURI, e.message)

        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(
            CommonApiResponse(
                success = false,
                errorCode = ErrorCode.INVALID_USER_INPUT.code,
                message = ErrorCode.INVALID_USER_INPUT.message
            )
        )
    }

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