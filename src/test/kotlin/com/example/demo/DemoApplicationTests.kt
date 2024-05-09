package com.example.demo

import org.junit.jupiter.api.Test
import org.springframework.boot.test.context.SpringBootTest
import java.time.LocalDate

@SpringBootTest
class DemoApplicationTests {

	@Test
	fun contextLoads() {
		val date1 = LocalDate.parse("2020-01-01")
		val date2 = LocalDate.parse("2020-02-02")
		println("${date1 < date2}")

	}

}
