import org.jetbrains.kotlin.gradle.tasks.KotlinCompile

plugins {
    id("org.springframework.boot")
    id("io.spring.dependency-management")
    kotlin("jvm")
    kotlin("plugin.spring")
}

val JWT_VERSION: String by project
allprojects{
    apply(plugin = "kotlin")

    group = "com.example"
    version = "0.0.2-SNAPSHOT"

    repositories {
        mavenCentral()
    }

    dependencies {
        implementation("org.mybatis.spring.boot:mybatis-spring-boot-starter:3.0.3")
        implementation("io.jsonwebtoken:jjwt-api:${JWT_VERSION}")
        runtimeOnly("io.jsonwebtoken:jjwt-impl:$JWT_VERSION")
        runtimeOnly("io.jsonwebtoken:jjwt-jackson:$JWT_VERSION")
        testImplementation(platform("org.junit:junit-bom:5.10.0"))
        testImplementation("org.junit.jupiter:junit-jupiter")
    }
}

/*tasks.withType<KotlinCompile> {
    kotlinOptions {
        freeCompilerArgs += "-Xjsr305=strict"
        jvmTarget = "17"
    }
}*/
