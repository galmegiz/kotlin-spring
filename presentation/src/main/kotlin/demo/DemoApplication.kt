package demo

import org.mybatis.spring.annotation.MapperScan
import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.context.properties.ConfigurationPropertiesScan
import org.springframework.boot.runApplication

/*
@ConfigurationPropertiesScan(basePackages = ["demo"])
@SpringBootApplication(scanBasePackages = ["demo"])
*/
@ConfigurationPropertiesScan(basePackages = ["domain"])
@SpringBootApplication(scanBasePackages = ["domain"])
@MapperScan(basePackages = ["domain"])
class DemoApplication

fun main(args: Array<String>) {
	runApplication<DemoApplication>(*args)
}
