package domain.common

import com.fasterxml.jackson.annotation.JsonIgnore
import org.slf4j.Logger
import org.slf4j.LoggerFactory

interface DomainLog {
    @get:JsonIgnore
    val log: Logger get() = LoggerFactory.getLogger(this::class.java.simpleName)
}