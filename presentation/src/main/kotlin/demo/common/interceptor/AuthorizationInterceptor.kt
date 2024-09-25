package demo.common.interceptor

import domain.common.ErrorCode
import domain.annotation.Authorization
import domain.auth.entity.User
import domain.auth.service.AuthorizationService
import domain.common.exception.SecurityException
import demo.common.interceptor.TokenVerifyInterceptor.Companion.USER_KEY
import jakarta.servlet.http.HttpServletRequest
import jakarta.servlet.http.HttpServletResponse
import org.springframework.stereotype.Component
import org.springframework.web.method.HandlerMethod
import org.springframework.web.servlet.HandlerInterceptor


@Component
class AuthorizationInterceptor(val authorizationService: AuthorizationService) : HandlerInterceptor {

    override fun preHandle(request: HttpServletRequest, response: HttpServletResponse, handler: Any): Boolean {
        val handlerMethod: HandlerMethod = handler as? HandlerMethod ?: return true
        if(!handlerMethod.hasMethodAnnotation(Authorization::class.java)) return true
        try {
            val authAnnotation = handlerMethod.method.getAnnotation(Authorization::class.java)
            val user: User = request.getAttribute(USER_KEY) as? User ?: return false
            check(authorizationService.authorize(user, authAnnotation.requiredRole))
        } catch (e: Exception) {
            throw SecurityException(ErrorCode.UNAUTHORIZED_ACCESS)
        }

        return true

    }
}