package org.jesperancinha.moving.objects

import io.swagger.v3.oas.annotations.OpenAPIDefinition
import io.swagger.v3.oas.annotations.info.Info
import io.swagger.v3.oas.annotations.servers.Server
import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.runApplication
import org.springframework.data.r2dbc.repository.config.EnableR2dbcRepositories
import org.springframework.security.config.annotation.web.reactive.EnableWebFluxSecurity
import org.springframework.web.reactive.config.EnableWebFlux

@SpringBootApplication
@EnableR2dbcRepositories
@EnableWebFluxSecurity
@OpenAPIDefinition(
	info = Info(title = "OpenAPI definition"),
	servers = [Server(url = "http://localhost:\${objects.endpoint.port}/objects", description = "Server URL")]
)
class MovingObjectsJWTServiceApplication

fun main(args: Array<String>) {
	runApplication<MovingObjectsJWTServiceApplication>(*args)
}
