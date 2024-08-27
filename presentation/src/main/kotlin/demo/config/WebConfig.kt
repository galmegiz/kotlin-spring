package demo.config

import org.springframework.context.annotation.Configuration
import org.springframework.web.method.support.HandlerMethodArgumentResolver
import org.springframework.web.servlet.config.annotation.InterceptorRegistry
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer
import demo.common.interceptor.AuthorizationInterceptor
import demo.common.interceptor.TokenVerifyInterceptor
import demo.common.resolver.UserArgumentResolver

@Configuration
class WebConfig(
    private val tokenVerifyInterceptor: TokenVerifyInterceptor,
    private val loginArgumentResolver: UserArgumentResolver,
    private val authorizationInterceptor: AuthorizationInterceptor
) : WebMvcConfigurer {

    override fun addInterceptors(registry: InterceptorRegistry) {
        registry.addInterceptor(tokenVerifyInterceptor)
            .addPathPatterns("/**")
            .excludePathPatterns("/employee/name/**")
            .excludePathPatterns("/user/*")
            .excludePathPatterns("/auth/*")
            .excludePathPatterns("/h2-console/*")
            .excludePathPatterns("/favicon.ico")
            .excludePathPatterns("/test/**")
            .excludePathPatterns("/external/**")

        registry.addInterceptor(authorizationInterceptor)
            .addPathPatterns("/**")
    }

    override fun addArgumentResolvers(resolvers: MutableList<HandlerMethodArgumentResolver>) {
        resolvers.add(loginArgumentResolver)
    }
}