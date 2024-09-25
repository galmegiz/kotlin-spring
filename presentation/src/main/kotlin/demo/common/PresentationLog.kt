package demo.common

import com.fasterxml.jackson.annotation.JsonIgnore
import org.slf4j.Logger
import org.slf4j.LoggerFactory

interface PresentationLog {
    @get:JsonIgnore
    val log: Logger get() = LoggerFactory.getLogger(this::class.java.simpleName)
}