package org.jesperancinha.moving.objects.rest

import org.jesperancinha.moving.objects.domain.MovingObjectService
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController

/**
 * Created by jofisaes on 29/07/2022
 */
@RestController
@RequestMapping
class ObjectsController(
    val movingObjectsService: MovingObjectService
) {
    @GetMapping("/jwt/open")
    fun getWelcome() = "Welcome to the Objects Cameras Service Exercise!"

    @GetMapping("/jwt")
    fun getProtectedTest() = "This should be protected!"

    @GetMapping("/jwt/open/all")
    fun getAll() = movingObjectsService.getAll()

    @GetMapping("/all")
    fun getAllProtected() = movingObjectsService.getAll()
}