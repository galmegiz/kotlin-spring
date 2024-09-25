package domain.common.config

import domain.common.DomainLog
import io.github.resilience4j.circuitbreaker.CircuitBreaker
import io.github.resilience4j.core.registry.EntryAddedEvent
import io.github.resilience4j.core.registry.EntryRemovedEvent
import io.github.resilience4j.core.registry.EntryReplacedEvent
import io.github.resilience4j.core.registry.RegistryEventConsumer
import io.github.resilience4j.retry.Retry
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration

@Configuration
class ResilienceConfig : DomainLog {
    @Bean
    fun retryEventConsumer(): RegistryEventConsumer<Retry> {
        return object : RegistryEventConsumer<Retry> {
            override fun onEntryAddedEvent(entryAddedEvent: EntryAddedEvent<Retry>) {
                entryAddedEvent.addedEntry.eventPublisher.onEvent { event -> log.info(event.toString()) }
            }

            override fun onEntryRemovedEvent(entryRemoveEvent: EntryRemovedEvent<Retry>) {
                TODO("Not yet implemented")
            }

            override fun onEntryReplacedEvent(entryReplacedEvent: EntryReplacedEvent<Retry>) {
                TODO("Not yet implemented")
            }
        }
    }

    @Bean
    fun circuitBreakerEventConsumer(): RegistryEventConsumer<CircuitBreaker> {
        return object : RegistryEventConsumer<CircuitBreaker> {
            override fun onEntryAddedEvent(entryAddedEvent: EntryAddedEvent<CircuitBreaker>) {
                val eventPublisher = entryAddedEvent.addedEntry.eventPublisher

                eventPublisher.onEvent { event -> log.info("{}", event) }
                eventPublisher.onCallNotPermitted { event -> log.info("{}", event) } // open 상태에서 요청이 들어온 경우
                eventPublisher.onStateTransition { event -> log.info("{}", event) } // state가 변경될 경우 // 다른 서버로 장애 전파할 때 사용할 수 있음
                eventPublisher.onFailureRateExceeded { event -> log.info("{}", event.eventType) }
            }

            override fun onEntryRemovedEvent(entryRemoveEvent: EntryRemovedEvent<CircuitBreaker>) {
                TODO("Not yet implemented")
            }

            override fun onEntryReplacedEvent(entryReplacedEvent: EntryReplacedEvent<CircuitBreaker>) {
                TODO("Not yet implemented")
            }
        }
    }
}