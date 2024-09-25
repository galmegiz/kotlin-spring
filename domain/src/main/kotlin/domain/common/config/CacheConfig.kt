package domain.common.config

import com.github.benmanes.caffeine.cache.Caffeine
import com.github.benmanes.caffeine.cache.RemovalCause
import domain.common.constant.CacheType
import domain.common.constant.Gender
import org.springframework.cache.CacheManager
import org.springframework.cache.annotation.EnableCaching
import org.springframework.cache.caffeine.CaffeineCache
import org.springframework.cache.support.SimpleCacheManager
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import java.util.concurrent.TimeUnit

@Configuration
@EnableCaching
class CacheConfig {
    @Bean
    fun cacheManager(): CacheManager {
        val caches = CacheType.entries.map { cache ->
            CaffeineCache(
                cache.cacheName,
                Caffeine.newBuilder()
                    .initialCapacity(cache.capacity)
                    .expireAfterAccess(cache.expireTime, TimeUnit.SECONDS)
                    .evictionListener<Any, Any>{key, _, cause -> println("cache eviction! $key :: $cause")}
                    .maximumSize(cache.maximumSize)
                    .build()
            )
        }

        val cacheManager = SimpleCacheManager()
        cacheManager.setCaches(caches)
        return cacheManager
    }
}