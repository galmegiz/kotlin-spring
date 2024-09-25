package demo

import org.mybatis.spring.annotation.MapperScan
import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.context.properties.ConfigurationPropertiesScan
import org.springframework.boot.runApplication

@ConfigurationPropertiesScan(basePackages = ["demo", "domain"])
@SpringBootApplication(scanBasePackages = ["demo", "domain"])
@MapperScan(basePackages = ["domain.auth.repository.mapper", "domain.employee.repository.mapper"])
class DemoApplication

fun main(args: Array<String>) {
	runApplication<DemoApplication>(*args)
}
