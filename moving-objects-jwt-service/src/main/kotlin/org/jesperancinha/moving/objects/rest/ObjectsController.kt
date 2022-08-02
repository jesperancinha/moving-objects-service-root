package org.jesperancinha.moving.objects.rest

import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.withContext
import org.jesperancinha.moving.objects.domain.MovingObjectService
import org.springframework.http.MediaType
import org.springframework.web.bind.annotation.*
import reactor.core.publisher.Flux
import reactor.core.publisher.Mono
import java.math.BigInteger


/**
 * Created by jofisaes on 29/07/2022
 */
@RestController
@RequestMapping("/webcams")
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
    ): ByteArray? = withContext(Dispatchers.IO) {
        movingObjectsService.getImageByteArray(code)
    }

    @GetMapping(value = ["/jwt/open/camera/{code}"], produces = [MediaType.IMAGE_JPEG_VALUE])
    @ResponseBody
    suspend fun getImage(
        @PathVariable("code")
        code: String
    ): ByteArray? = withContext(Dispatchers.IO) {
        movingObjectsService.getImageByteArray(code)
    }

    @GetMapping("/page/{pageSize}/{pageOffSet}")
    @ResponseBody
    fun getPageBySizeAndOffSetProtected(
        @PathVariable("pageSize")
        pageSize: Int,
        @PathVariable("pageOffSet")
        pageOffSet: Int
    ): Mono<Page> = movingObjectsService.getPageBySizeAndOffSet(pageSize, pageOffSet)

    @GetMapping("/{pageSize}/{pageOffSet}")
    @ResponseBody
    fun getWebamsPageBySizeAndOffSetProtected(
        @PathVariable("pageSize")
        pageSize: Int,
        @PathVariable("pageOffSet")
        pageOffSet: Int
    ): Flux<WebCamSource> = movingObjectsService.getWebcamsPageBySizeAndOffSet(pageSize, pageOffSet)

    @GetMapping("/jwt/open/page/{pageSize}/{pageOffSet}")
    @ResponseBody
    fun getPageBySizeAndOffSet(
        @PathVariable("pageSize")
        pageSize: Int,
        @PathVariable("pageOffSet")
        pageOffSet: Int
    ): Mono<Page> = movingObjectsService.getPageBySizeAndOffSet(pageSize, pageOffSet)

    @GetMapping("/jwt/open/page/coroutines/{pageSize}/{pageOffSet}")
    @ResponseBody
    suspend fun getPageBySizeAndOffSetWithCoroutines(
        @PathVariable("pageSize")
        pageSize: Int,
        @PathVariable("pageOffSet")
        pageOffSet: Int
    ): Page = movingObjectsService.getPageBySizeAndOffSetWithCoroutines(pageSize, pageOffSet)

    @GetMapping("/location/{x}/{y}/{radius}")
    fun getCamerasByLocation(
        @PathVariable
        x: BigInteger,
        @PathVariable
        y: BigInteger,
        @PathVariable
        radius: BigInteger
    ): Flow<WebCamSource> = movingObjectsService.getCamerasByLocation(x, y, radius)

}