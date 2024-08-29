package demo.common.resolver

import demo.common.interceptor.TokenVerifyInterceptor
import domain.common.ErrorCode
import domain.annotation.LoginUser
import domain.auth.entity.User
import domain.common.exception.SecurityException
import jakarta.servlet.http.HttpServletRequest
import org.springframework.core.MethodParameter
import org.springframework.stereotype.Component
import org.springframework.web.bind.support.WebDataBinderFactory
import org.springframework.web.context.request.NativeWebRequest
import org.springframework.web.method.support.HandlerMethodArgumentResolver
import org.springframework.web.method.support.ModelAndViewContainer

@Component
class UserArgumentResolver : HandlerMethodArgumentResolver {
    override fun supportsParameter(parameter: MethodParameter): Boolean {
        return parameter.hasParameterAnnotation(LoginUser::class.java)
    }

    override fun resolveArgument(
        parameter: MethodParameter,
        mavContainer: ModelAndViewContainer?,
        webRequest: NativeWebRequest,
        binderFactory: WebDataBinderFactory?
    ): User {
        return webRequest.getNativeRequest(HttpServletRequest::class.java)
            ?.getAttribute(TokenVerifyInterceptor.USER_KEY) as User?
            ?: throw SecurityException(ErrorCode.USER_NOT_FOUND)
    }
}