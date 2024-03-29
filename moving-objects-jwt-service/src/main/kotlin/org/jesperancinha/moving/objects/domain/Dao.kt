package org.jesperancinha.moving.objects.domain

import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.fold
import kotlinx.coroutines.flow.map
import org.jesperancinha.moving.objects.rest.*
import org.springframework.beans.factory.annotation.Value
import org.springframework.data.annotation.Id
import org.springframework.data.domain.PageRequest
import org.springframework.data.domain.Pageable
import org.springframework.data.r2dbc.repository.Query
import org.springframework.data.relational.core.mapping.Column
import org.springframework.data.relational.core.mapping.Table
import org.springframework.data.repository.kotlin.CoroutineCrudRepository
import org.springframework.stereotype.Service
import reactor.core.publisher.Flux
import reactor.core.publisher.Mono
import java.math.BigInteger
import java.nio.file.Files.walk
import java.nio.file.Path
import java.time.LocalDateTime
import java.util.*
import kotlin.io.path.name
import kotlin.io.path.readBytes
import kotlin.math.absoluteValue

@Table
data class MovingObject(
    @Id val id: UUID? = null,
    @Column("code") val code: String,
    @Column("folder") val folder: String,
    @Column("uri") val uri: String,
    @Column("x") val x: Int,
    @Column("y") val y: Int
) : Comparable<MovingObject> {
    override fun compareTo(other: MovingObject): Int = id?.compareTo(other.id) ?: 0
}

@Table
data class InfoObject(
    @Id val id: UUID? = null,
    @Column("name") val name: String,
    @Column("code") val code: String,
    @Column("size") val size: Int,
    @Column("color") val color: String,
    @Column("x") val x: Int,
    @Column("y") val y: Int
)

interface MovingObjectRepository : CoroutineCrudRepository<MovingObject, String> {
    suspend fun findByCode(code: String): MovingObject

    fun findAllBy(pageable: Pageable): Flux<MovingObject>

    @Query("select mos.* from moving_object as mos where sqrt(pow(mos.x-:x, 2) + pow(mos.y-:y, 2)) <= :radius")
    fun findAllCamerasInRadiusFrom(x: BigInteger, y: BigInteger, radius: BigInteger): Flow<MovingObject>
}

interface MovingObjectCoRepository : CoroutineCrudRepository<MovingObject, String> {
    fun findAllBy(pageable: Pageable): Flow<MovingObject>
}

interface InfoObjectRepository : CoroutineCrudRepository<InfoObject, String> {
    suspend fun findByCode(code: String): InfoObject

    @Query("Select io.* from info_object as io where io.name like concat('%',:searchTerm,'%') or io.color like concat('%',:searchTerm,'%') or io.code like concat('%',:searchTerm,'%')")
    fun findBySearchTerm(searchTerm: String): Flow<InfoObject>
}

@Service
class MovingObjectService(
    val movingObjectRepository: MovingObjectRepository,
    val movingObjectCoRepository: MovingObjectCoRepository,
    @Value("\${objects.jwt.webcams.baseUrl}")
    val baseUrl: String
) {
    fun getAll() = movingObjectRepository.findAll().map { it.toWebcamSource(baseUrl) }

    suspend fun getImageByteArray(code: String) =
        movingObjectRepository.findByCode(code)
            .let { mo ->
                Path.of(System.getProperty("user.dir"), "cameras", mo.folder)
                    .let { resource ->
                        val allImages =
                            walk(resource).use { paths ->
                                val filter = paths
                                    .sorted()
                                    .filter { it.name.endsWith("jpg") }
                                filter.toList()
                            }
                        if (allImages.size > 0) {
                            val countImages = allImages?.size ?: 0
                            val delta = (10 / countImages.toDouble())
                            val currentMinute = LocalDateTime.now().second.toString().last().digitToInt()
                            val index = (((currentMinute + 1) / delta).toInt()).absoluteValue - 1
                            allImages?.get(if (index == -1) 0 else index)?.let { resource.resolve(it.name) }
                        } else null
                    }?.readBytes()
            }

    fun getPageBySizeAndOffSet(pageSize: Int, pageOffSet: Int): Mono<PageLocationDto> =
        movingObjectRepository.findAllBy(PageRequest.of(pageOffSet, pageSize)).toPage(pageSize, pageOffSet, baseUrl)

    fun getWebcamsPageBySizeAndOffSet(pageSize: Int, pageOffSet: Int): Flux<WebCamSourceDto> =
        movingObjectRepository.findAllBy(PageRequest.of(pageOffSet, pageSize)).map { it.toWebcamSource(baseUrl) }

    suspend fun getPageBySizeAndOffSetWithCoroutines(pageSize: Int, pageOffSet: Int): PageLocationDto =
        movingObjectCoRepository.findAllBy(PageRequest.of(pageOffSet, pageSize)).toPage(pageSize, pageOffSet, baseUrl)

    fun getCamerasByLocation(x: BigInteger, y: BigInteger, radius: BigInteger): Flow<WebCamSourceDto> =
        movingObjectRepository.findAllCamerasInRadiusFrom(x, y, radius).map { it.toWebcamSource(baseUrl) }
}

@Service
class InfoObjectService(
    val infoObjectRepository: InfoObjectRepository
) {
    fun getAll() = infoObjectRepository.findAll().map { it.toObjectSourceDto }

    suspend fun getByCodeId(codeId: String): InfoObjectSourceDto =
        infoObjectRepository.findByCode(codeId).toObjectSourceDto


    fun getAllBySearchItem(searchTerm: String): Flow<InfoObjectSourceDto> =
        infoObjectRepository.findBySearchTerm(searchTerm).map { it.toObjectSourceDto }
}


/**
 * Looks better and more immutable than Kotlin Coroutines
 */
fun Flux<MovingObject>.toPage(pageSize: Int, pageOffSet: Int, baseUrl: String) =
    collectSortedList().map {
        PageLocationDto(
            pageSize = pageSize,
            totalElements = it.size,
            pageNumber = pageOffSet,
            totalPages = 10000,
            movingObjects = LocationObjectSourcesDto(
                movingObjects = it.map { mo -> mo.toMovingObjectSource(baseUrl) }.toMutableList()
            )
        )

    }

/**
 * I had to use mutable to support kotlin function fold. There doesn't see to be a better way to use kotlin coroutines to go from a reactive list to a mono / suspend object.
 */
suspend fun Flow<MovingObject>.toPage(pageSize: Int, pageOffSet: Int, baseUrl: String) =
    fold(
        PageLocationDto(
            pageSize = pageSize,
            totalElements = 0,
            pageNumber = pageOffSet,
            totalPages = 10000,
            movingObjects = LocationObjectSourcesDto(
                movingObjects = mutableListOf()
            )
        )
    ) { page, mo ->
        page.totalPages++
        page.movingObjects.movingObjects.add(mo.toMovingObjectSource(baseUrl))
        page
    }

private val InfoObject.toObjectSourceDto: InfoObjectSourceDto
    get() = InfoObjectSourceDto(
        name = name,
        code = code,
        size = size,
        color = color,
        coordinates = CoordinateSourceDto(x.toBigDecimal(), y.toBigDecimal())
    )

fun MovingObject.toMovingObjectSource(baseUrl: String) = LocationObjectSourceDto(
    code = this.code,
    city = "Olhão",
    themeList = emptyList(),
    coordinates = CoordinateSourceDto(
        x = this.x.toBigDecimal(),
        y = this.y.toBigDecimal()
    ),
    pointsOfSale = emptyList(),
    webCamSources = listOf(toWebcamSource(baseUrl))
)

private fun MovingObject.toWebcamSource(baseUrl: String) = WebCamSourceDto(
    coordinates = CoordinateSourceDto(
        x = this.x.toBigDecimal(),
        y = this.y.toBigDecimal()
    ),
    wikiInfo = "",
    active = true,
    webCamImage = "${baseUrl}${this.uri}".let { url ->
        WebCamImageSourceDto(
            iconUrl = url,
            thumbnailUrl = url,
            previewUrl = url,
            toenailUrlString = url
        )
    }
)

