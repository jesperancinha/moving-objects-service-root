package org.jesperancinha.moving.objects

import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.runApplication
import org.springframework.data.r2dbc.repository.config.EnableR2dbcRepositories
import org.springframework.security.config.annotation.web.reactive.EnableWebFluxSecurity
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController
import org.springframework.web.reactive.config.EnableWebFlux

@SpringBootApplication
@EnableR2dbcRepositories
@EnableWebFluxSecurity
@EnableWebFlux
class MovingObjectsJWTServiceApplication

fun main(args: Array<String>) {
	runApplication<MovingObjectsJWTServiceApplication>(*args)
}
