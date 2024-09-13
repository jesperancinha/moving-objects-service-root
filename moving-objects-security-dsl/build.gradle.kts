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
    api("org.apache.commons:commons-math3:3.6.1")
    implementation("com.okta.spring:okta-spring-boot-starter:3.0.7")
    implementation("com.fasterxml.jackson.module:jackson-module-kotlin:2.17.2")
    implementation("me.paulschwarz:spring-dotenv:4.0.0")
    implementation("org.springframework.security:spring-security-web:6.3.3")
    implementation("org.springframework:spring-webflux:6.1.12")
    testImplementation("org.springframework.boot:spring-boot-starter-test:3.3.3")
}

val gradleSysVersion = System.getenv("GRADLE_VERSION")

tasks.register<Wrapper>("wrapper") {
    gradleVersion = gradleSysVersion
}

tasks.register("prepareKotlinBuildScriptModel"){}
