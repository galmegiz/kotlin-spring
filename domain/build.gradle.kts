import org.springframework.boot.gradle.tasks.bundling.BootJar

plugins {
	id("org.springframework.boot")
	id("io.spring.dependency-management")
	kotlin("jvm")
	kotlin("plugin.spring")
}

val JWT_VERSION: String by project
val MYBATIS_VERSION: String by project
dependencies {
	implementation("org.springframework.boot:spring-boot-starter-web")
	implementation("org.springframework.boot:spring-boot-starter-actuator")
	implementation("org.springframework.boot:spring-boot-starter-aop")

	implementation("org.mybatis.spring.boot:mybatis-spring-boot-starter:${MYBATIS_VERSION}")
	testImplementation("org.mybatis.spring.boot:mybatis-spring-boot-starter-test:${MYBATIS_VERSION}")
	implementation("org.springframework.boot:spring-boot-starter-cache")

	implementation("com.github.ben-manes.caffeine:caffeine:3.1.8")


	implementation("org.springframework.security:spring-security-crypto")
	implementation("org.springframework.boot:spring-boot-starter-validation")

	implementation("io.github.resilience4j:resilience4j-spring-boot3:2.1.0")
	implementation("io.github.resilience4j:resilience4j-all:2.1.0") // Optional, only required when you want to use the Decorators class

	runtimeOnly("com.h2database:h2")

	testImplementation("org.springframework.boot:spring-boot-starter-test")

	implementation("org.springframework.boot:spring-boot-starter-aop")
	implementation("org.jetbrains.kotlin:kotlin-reflect")
}

tasks.withType<Test> {
	useJUnitPlatform()
}

val jar: Jar by tasks
val bootJar: BootJar by tasks

bootJar.enabled = false
jar.enabled = true