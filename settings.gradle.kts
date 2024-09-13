pluginManagement {
    repositories {
        mavenLocal()
        gradlePluginPortal()
        mavenCentral()
    }
}

rootProject.name = "moving-objects-service-root"
include("moving-objects-rest-service")
include("moving-objects-jwt-service")
include("moving-objects-security-dsl")
