package com.example.config

import com.example.common.interceptor.TokenVerifyInterceptor
import com.example.common.resolver.UserArgumentResolver
import com.example.common.util.TokenUtil
import org.springframework.context.annotation.Configuration
import org.springframework.web.method.support.HandlerMethodArgumentResolver
import org.springframework.web.servlet.config.annotation.InterceptorRegistry
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer

@Configuration
class WebConfig(
    private val tokenVerifyInterceptor: TokenVerifyInterceptor,
    private val loginArgumentResolver: UserArgumentResolver
) : WebMvcConfigurer {

    override fun addInterceptors(registry: InterceptorRegistry) {
        registry.addInterceptor(tokenVerifyInterceptor)
            .addPathPatterns("/**")
            .excludePathPatterns("/employee/name/**")
            .excludePathPatterns("/user/*")
            .excludePathPatterns("/auth/*")
            .excludePathPatterns("/h2-console/*")
            .excludePathPatterns("/favicon.ico")
    }

    override fun addArgumentResolvers(resolvers: MutableList<HandlerMethodArgumentResolver>) {
        resolvers.add(loginArgumentResolver)
    }
}