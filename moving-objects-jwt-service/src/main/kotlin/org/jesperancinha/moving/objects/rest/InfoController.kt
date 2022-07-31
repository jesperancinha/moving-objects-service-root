package org.jesperancinha.moving.objects.rest

import org.jesperancinha.moving.objects.domain.InfoObjectService
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PathVariable
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController

@RestController
@RequestMapping("/info")
class InfoController(
    val infoObjectService: InfoObjectService
) {

    @GetMapping("/jwt/open")
    fun getWelcome() = "Welcome to the Objects Cameras Service Exercise!"

    @GetMapping("/jwt")
    fun getProtectedTest() = "This should be protected!"

    @GetMapping("/jwt/open/all")
    fun getAll() = infoObjectService.getAll()

    @GetMapping("/all")
    fun getAllProtected() = infoObjectService.getAll()

    @GetMapping("/code/{codeId}")
    suspend fun getByCodeId(
        @PathVariable("codeId")
        codeId: String
    ) = infoObjectService.getByCodeId(codeId)

}