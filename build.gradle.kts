buildscript {
    repositories {
        mavenLocal()
        mavenCentral()
    }
}


plugins {
    jacoco
    id("org.jesperancinha.plugins.omni") version "0.4.5"
    id("java")
    id("idea")
    id("maven-publish")
}

omniConfig {
    extraSourceFolders = listOf(File("$rootDir/moving-objects-gui"))
    extraReportFolders = listOf(File("$rootDir/moving-objects-gui/coverage"))
}

allprojects {
    repositories {
        mavenLocal()
        mavenCentral()
    }
}

version "1.0.0"
group "org.jesperancinha.objects"

repositories {
    mavenLocal()
    mavenCentral()
}

subprojects {
    apply(plugin = "java")
    apply(plugin = "maven-publish")
    version = "1.0.0"
}

tasks.test {
    finalizedBy(tasks.jacocoTestReport)
}

tasks.jacocoTestReport {
    dependsOn(tasks.test, subprojects.map { it.tasks.test })

    reports {
        xml.required.set(true)
        csv.required.set(true)
    }
}

