package demo

import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.context.properties.ConfigurationPropertiesScan
import org.springframework.boot.runApplication

/*
@ConfigurationPropertiesScan(basePackages = ["demo"])
@SpringBootApplication(scanBasePackages = ["demo"])
*/
@ConfigurationPropertiesScan(basePackages = ["demo"])
@SpringBootApplication(scanBasePackages = ["demo"])
class DemoApplication

fun main(args: Array<String>) {
	runApplication<DemoApplication>(*args)
}
