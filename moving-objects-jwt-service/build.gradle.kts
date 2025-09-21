import org.jetbrains.kotlin.gradle.dsl.JvmTarget.JVM_21
import org.jetbrains.kotlin.gradle.tasks.KotlinCompile

buildscript {
    repositories {
        mavenLocal()
        mavenCentral()
    }
}


plugins {
    alias(libs.plugins.spring.boot)
    alias(libs.plugins.spring.dependency.management)
    alias(libs.plugins.kotlin.jvm)
    alias(libs.plugins.kotlin.spring)
    alias(libs.plugins.openapi.gradle.plugin)
    jacoco
    `maven-publish`
}

group = "org.jesperancinha"
version = "0.0.1-SNAPSHOT"
java.sourceCompatibility = JavaVersion.VERSION_25

tasks {
    bootJar {
        archiveFileName.set("${project.name}.jar")
    }
}

repositories {
    mavenCentral()
}

dependencies {
    implementation(kotlin("stdlib"))
    implementation(platform(libs.spring.boot.bom))
    implementation("org.springframework.boot:spring-boot-starter-data-r2dbc")
    implementation("org.springframework.boot:spring-boot-starter-data-jpa")
    implementation("org.springframework.boot:spring-boot-starter-oauth2-resource-server")
    implementation("org.springframework.boot:spring-boot-starter-webflux")
    implementation("org.springframework.boot:spring-boot-starter-actuator")
    implementation("com.fasterxml.jackson.module:jackson-module-kotlin")
    implementation(libs.reactor.kotlin.extensions)
    implementation(libs.r2dbc.postgresql)
    implementation(libs.micrometer.registry.prometheus)
    implementation(libs.kotlin.reflect)
    implementation(libs.kotlin.stdlib)
    implementation(libs.kotlinx.coroutines.reactor)
    implementation(libs.springdoc.openapi.starter.webflux.ui)
    implementation(libs.springdoc.openapi.starter.common)
    testImplementation("org.springframework.boot:spring-boot-starter-test") {
        exclude(group = "org.junit.vintage", module = "junit-vintage-engine")
        exclude(group = "org.junit.platform", module = "junit-platform-commons")
        exclude(group = "org.junit.platform", module = "junit-platform-engine")
    }
    testImplementation(libs.reactor.test)
    testImplementation(platform("org.junit:junit-bom:${libs.versions.junit.jupiter.get()}"))
    testImplementation(libs.junit.jupiter)
    testRuntimeOnly(libs.junit.platform.launcher)
    testRuntimeOnly("org.junit.platform:junit-platform-engine:${libs.versions.junit.platform.get()}")
    testRuntimeOnly("org.junit.platform:junit-platform-commons:${libs.versions.junit.platform.get()}")
}

kotlin {
    jvmToolchain(25)
}

tasks.withType<KotlinCompile>().configureEach {
    compilerOptions {
        freeCompilerArgs.add("-Xjsr305=strict")
        jvmTarget.set(JVM_25)
    }
}

tasks.withType<Test> {
    useJUnitPlatform()
}

tasks.register<Wrapper>("wrapper")

tasks.register("prepareKotlinBuildScriptModel") {}
