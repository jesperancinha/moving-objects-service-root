package org.jesperancinha.moving.objects

import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.runApplication
import org.springframework.data.r2dbc.repository.config.EnableR2dbcRepositories
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController

@SpringBootApplication
@EnableR2dbcRepositories
class MovingObjectsOauthServiceApplication

fun main(args: Array<String>) {
	runApplication<MovingObjectsOauthServiceApplication>(*args)
}


@RestController
@RequestMapping
class Controller {
	@GetMapping("/")
	fun getWhat() = "what"
}