package org.jesperancinha.moving.objects.rest

import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController

/**
 * Created by jofisaes on 29/07/2022
 */
@RestController
@RequestMapping
class ObjectsController {
    @GetMapping("/jwt/open")
    fun getWelcome() = "Welcome to the Objects Cameras Service Exercise!"

    @GetMapping("/jwt")
    fun getProtectedTest() = "This should be protected!"
}