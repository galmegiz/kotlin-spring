package com.example.config

import com.example.common.Log
import io.github.resilience4j.core.registry.EntryAddedEvent
import io.github.resilience4j.core.registry.EntryRemovedEvent
import io.github.resilience4j.core.registry.EntryReplacedEvent
import io.github.resilience4j.core.registry.RegistryEventConsumer
import io.github.resilience4j.retry.Retry
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import org.springframework.context.annotation.EnableAspectJAutoProxy

@Configuration
class ResilienceConfig : Log {

    @Bean
    fun registryEventConsumer(): RegistryEventConsumer<Retry> {
        return object : RegistryEventConsumer<Retry>{
            override fun onEntryAddedEvent(entryAddedEvent: EntryAddedEvent<Retry>) {
                entryAddedEvent.addedEntry.eventPublisher.onEvent{event -> log.info(event.toString())}
            }

            override fun onEntryRemovedEvent(entryRemoveEvent: EntryRemovedEvent<Retry>) {
                log.info("entry removed")
            }

            override fun onEntryReplacedEvent(entryReplacedEvent: EntryReplacedEvent<Retry>) {
                log.info("replaced event")
            }
        }
    }
}