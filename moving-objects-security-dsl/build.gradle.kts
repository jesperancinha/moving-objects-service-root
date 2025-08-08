buildscript {
    repositories {
        mavenLocal()
        mavenCentral()
    }
}
plugins {
    id("java-library")
    jacoco
}
apply(plugin = "maven-publish")

version = "1.0.0"
group = "org.jesperancinha.objects"

repositories {
    mavenLocal()
    mavenCentral()
}

java {
    toolchain {
        languageVersion = JavaLanguageVersion.of(21)
    }
}

publishing {
    publications {
        create<MavenPublication>("maven") {
            groupId = "org.jesperancinha.objects"
            artifactId = "moving-objects-security-dsl"
            version = "1.0.0"

            from(components["java"])
        }
    }
}

tasks.jacocoTestReport {
    dependsOn(tasks.test)

    reports {
        xml.required.set(true)
        csv.required.set(true)
    }
}

tasks.named<Test>("test") {
    useJUnitPlatform()

    maxHeapSize = "1G"

    testLogging {
        events("passed")
    }
}

dependencies {
    api(libs.commons.math3)
    implementation(libs.okta.spring.boot.starter)
    implementation(libs.jackson.module.kotlin)
    implementation(libs.spring.dotenv)
    implementation(libs.spring.security.web)
    implementation(libs.spring.webflux)
    testImplementation(libs.spring.boot.starter.test)
    testImplementation(libs.junit.jupiter.api)
    testImplementation(libs.junit.jupiter.engine)
    testImplementation(libs.junit.platform.launcher)
}

tasks.register<Wrapper>("wrapper")

tasks.register("prepareKotlinBuildScriptModel"){}
