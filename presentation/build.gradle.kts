import org.springframework.boot.gradle.tasks.bundling.BootJar

plugins {
    id("org.springframework.boot")
    id("io.spring.dependency-management")
    kotlin("jvm")
    kotlin("plugin.spring")
}

val JWT_VERSION: String by project
val MYBATIS_VERSION: String by project
val bootJar: BootJar by tasks

dependencies {
    implementation("org.springframework.boot:spring-boot-starter-web")
    implementation("org.mybatis.spring.boot:mybatis-spring-boot-starter:${MYBATIS_VERSION}")
    testImplementation("org.mybatis.spring.boot:mybatis-spring-boot-starter-test:${MYBATIS_VERSION}")
    implementation("org.springframework.boot:spring-boot-starter-actuator")
    implementation("org.springframework.boot:spring-boot-starter-aop")
    implementation(project(":domain"))
}
bootJar.mainClass = "demo.DemoApplication.kt"


