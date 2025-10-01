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
    implementation(platform(libs.spring.boot.bom))
    implementation(libs.okta.spring.boot.starter)
    implementation("com.fasterxml.jackson.module:jackson-module-kotlin")
    implementation(libs.spring.dotenv)
    implementation("org.springframework.security:spring-security-web")
    implementation("org.springframework:spring-webflux")
    testImplementation("org.springframework.boot:spring-boot-starter-test")
    testImplementation(platform(libs.junit.jupiter.bom))
    testImplementation("org.junit.jupiter:junit-jupiter-api")
    testRuntimeOnly("org.junit.platform:junit-platform-launcher")
    testRuntimeOnly("org.junit.platform:junit-platform-engine")
    testRuntimeOnly("org.junit.platform:junit-platform-commons")
}

tasks.register<Wrapper>("wrapper")

tasks.register("prepareKotlinBuildScriptModel"){}
