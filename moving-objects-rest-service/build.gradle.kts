buildscript {
    repositories {
        mavenLocal()
        mavenCentral()
    }
}

plugins {
    application
    jacoco
    alias(libs.plugins.spring.boot)
    alias(libs.plugins.spring.dependency.management)
}

version "1.0.0"
group "org.jesperancinha.objects"

apply(plugin = "application")
apply(plugin = "java")
apply(plugin = "idea")
apply(plugin = "eclipse")
apply(plugin = "maven-publish")
apply(plugin = "jacoco")

application {
    applicationDefaultJvmArgs = listOf("-XX:+AllowRedefinitionToAddDeleteMethods")
}

configurations {
    create("testCompile")
}

repositories {
    mavenLocal()
    mavenCentral()
}

tasks.bootJar {
    mainClass.set("org.jesperancinha.objects.ObjectsSupportLauncher")
}

java {
    toolchain {
        languageVersion = JavaLanguageVersion.of(21)
    }
}

tasks.test {
    finalizedBy(tasks.jacocoTestReport)
    useJUnitPlatform()
    jvmArgs = listOf("-XX:+AllowRedefinitionToAddDeleteMethods")
}

tasks.jacocoTestReport {
    dependsOn(tasks.test)

    reports {
        xml.required.set(true)
        csv.required.set(true)
    }
}

tasks.compileJava {
    options.annotationProcessorPath = configurations.annotationProcessor.get()
}

tasks.bootJar {
    archiveFileName.set("${project.name}.jar")
}

dependencies {
    if (project.hasProperty("prod")) {
        implementation("com.okta.spring:okta-spring-boot-starter:3.0.7")
        implementation("com.fasterxml.jackson.module:jackson-module-kotlin:2.17.2")
        implementation("me.paulschwarz:spring-dotenv:4.0.0")
        implementation("org.springframework.security:spring-security-web:6.3.3")
        implementation("org.jesperancinha.objects:moving-objects-security-dsl:1.0.0")
    }
    implementation(kotlin("stdlib"))
    implementation("org.springframework.boot:spring-boot-starter-actuator")
    implementation("io.micrometer:micrometer-core:1.13.3")
    implementation("io.micrometer:micrometer-registry-prometheus:1.13.3")
    implementation("org.springframework.data:spring-data-commons")
    implementation("org.springframework.boot:spring-boot-starter-webflux")
    implementation("jakarta.json.bind:jakarta.json.bind-api:3.0.1")
    implementation("org.springdoc:springdoc-openapi-starter-webflux-ui:2.6.0")
    compileOnly("org.projectlombok:lombok:1.18.34")
    annotationProcessor("org.projectlombok:lombok:1.18.34")
    testImplementation("org.projectlombok:lombok:1.18.34")
    testAnnotationProcessor("org.projectlombok:lombok:1.18.34")
    testImplementation("org.projectlombok:lombok:1.18.34")
    testImplementation("jakarta.json.bind:jakarta.json.bind-api:3.0.1")
    testImplementation("org.springframework.boot:spring-boot-starter-test")
    testImplementation("com.github.tomakehurst:wiremock:3.0.1")
    testImplementation("io.projectreactor.tools:blockhound-junit-platform:1.0.9.RELEASE")
}

tasks.register<Wrapper>("wrapper")

tasks.register("prepareKotlinBuildScriptModel") {}
