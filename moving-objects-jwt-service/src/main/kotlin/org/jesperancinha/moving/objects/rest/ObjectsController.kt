package org.jesperancinha.moving.objects.rest

import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import org.jesperancinha.moving.objects.domain.MovingObjectService
import org.springframework.http.MediaType
import org.springframework.web.bind.annotation.*


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

    @GetMapping(value = ["/camera/{code}"], produces = [MediaType.IMAGE_JPEG_VALUE])
    @ResponseBody
    suspend fun getImageProtected(
        @PathVariable("code")
        code: String
    ): ByteArray? {
        return withContext(Dispatchers.IO) {
            javaClass
                .getResourceAsStream(movingObjectsService.getImagePathByCode(code).toString())?.readAllBytes()

        }
    }
    @GetMapping(value = ["/jwt/open//camera/{code}"], produces = [MediaType.IMAGE_JPEG_VALUE])
    @ResponseBody
    suspend fun getImage(
        @PathVariable("code")
        code: String
    ): ByteArray? {
        return withContext(Dispatchers.IO) {
            javaClass
                .getResourceAsStream(movingObjectsService.getImagePathByCode(code).toString())?.readAllBytes()

        }
    }
}