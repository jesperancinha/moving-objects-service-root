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

version = "1.0.0"
group = "org.jesperancinha.objects"

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
        implementation(libs.okta.spring.boot.starter)
        implementation(libs.jackson.module.kotlin)
        implementation(libs.spring.dotenv)
        implementation(libs.spring.security.web)
        implementation("org.jesperancinha.objects:moving-objects-security-dsl:1.0.0")
    }
    implementation(kotlin("stdlib"))
    implementation("org.springframework.boot:spring-boot-starter-actuator")
    implementation(libs.micrometer.core)
    implementation(libs.micrometer.registry.prometheus)
    implementation("org.springframework.data:spring-data-commons")
    implementation("org.springframework.boot:spring-boot-starter-webflux")
    implementation(libs.jakarta.json.bind.api)
    implementation(libs.springdoc.openapi.starter.webflux.ui)
    compileOnly(libs.lombok)
    annotationProcessor(libs.lombok)
    testImplementation(libs.lombok)
    testAnnotationProcessor(libs.lombok)
    testImplementation(libs.jakarta.json.bind.api)
    testImplementation("org.springframework.boot:spring-boot-starter-test")
    testImplementation(libs.wiremock)
    testImplementation(platform("org.junit:junit-bom:5.13.1"))
    testImplementation("org.junit.jupiter:junit-jupiter")
    testImplementation("org.junit.platform:junit-platform-suite")
    testImplementation(libs.blockhound.junit.platform)
}

tasks.register<Wrapper>("wrapper")

tasks.register("prepareKotlinBuildScriptModel") {}
