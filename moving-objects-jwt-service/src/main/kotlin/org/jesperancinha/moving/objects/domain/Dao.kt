package org.jesperancinha.moving.objects.domain

import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.fold
import kotlinx.coroutines.flow.map
import org.jesperancinha.moving.objects.rest.*
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
import java.nio.file.Files.walk
import java.time.LocalDateTime
import java.util.*
import kotlin.io.path.name
import kotlin.io.path.toPath
import kotlin.math.absoluteValue

@Table
data class MovingObject(
    @Id val id: UUID? = null,
    @Column("code") val code: String,
    @Column("folder") val folder: String,
    @Column("url") val url: String,
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
    @Column("color") val color: String
)

interface MovingObjectRepository : CoroutineCrudRepository<MovingObject, String> {
    suspend fun findByCode(code: String): MovingObject
    fun findAllBy(pageable: Pageable): Flux<MovingObject>
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
    val movingObjectCoRepository: MovingObjectCoRepository
) {
    fun getAll() = movingObjectRepository.findAll()

    suspend fun getImagePathByCode(code: String) =
        movingObjectRepository.findByCode(code)
            .let { mo ->
                "/${mo.folder}"
                    .let { resourcePath ->
                        val root = javaClass.getResource("/")?.toURI()?.toPath()
                        val resource = javaClass.getResource(resourcePath)
                        print(resource)
                        val allImages =
                            walk(resource?.toURI()?.toPath()).use { paths ->
                                val filter = paths
                                    .sorted()
                                    .filter { it.name.endsWith("jpg") }
                                filter.toList()
                            }
                        val countImages = allImages?.size ?: 0
                        val delta = (10 / countImages.toDouble())
                        val currentMinute = LocalDateTime.now().minute.toString().last().digitToInt()
                        val index = (((currentMinute + 1) / delta).toInt()).absoluteValue - 1
                        allImages?.get(index)?.let { "/${mo.folder}/${it.name}" }
                    }
            }

    fun getPageBySizeAndOffSet(pageSize: Int, pageOffSet: Int): Mono<Page> =
        movingObjectRepository.findAllBy(PageRequest.of(pageOffSet, pageSize)).toPage(pageSize, pageOffSet)

    suspend fun getPageBySizeAndOffSetWithCoroutines(pageSize: Int, pageOffSet: Int): Page =
        movingObjectCoRepository.findAllBy(PageRequest.of(pageOffSet, pageSize)).toPage(pageSize, pageOffSet)
}

@Service
class InfoObjectService(
    val infoObjectRepository: InfoObjectRepository
) {
    fun getAll() = infoObjectRepository.findAll()

    suspend fun getByCodeId(codeId: String): MovingObjectSource =
        infoObjectRepository.findByCode(codeId).toMovingObjectSource


    fun getAllBySearchItem(searchTerm: String): Flow<MovingObjectSource> =
        infoObjectRepository.findBySearchTerm(searchTerm).map { it.toMovingObjectSource }
}


/**
 * Looks better and more immutable than Kotlin Coroutines
 */
fun Flux<MovingObject>.toPage(pageSize: Int, pageOffSet: Int) =
    collectSortedList().map {
        Page(
            pageSize = pageSize,
            totalElements = it.size,
            pageNumber = pageOffSet,
            totalPages = 10000,
            movingObjects = MovingObjects(
                movingObjects = it.map { mo -> mo.toMovingObjectSource }.toMutableList()
            )
        )

    }

/**
 * I had to use mutable to support kotlin function fold. There doesn't see to be a better way to use kotlin coroutines to go from a reactive list to a mono / suspend object.
 */
suspend fun Flow<MovingObject>.toPage(pageSize: Int, pageOffSet: Int) =
    fold(
        Page(
            pageSize = pageSize,
            totalElements = 0,
            pageNumber = pageOffSet,
            totalPages = 10000,
            movingObjects = MovingObjects(
                movingObjects = mutableListOf()
            )
        )
    ) { page, mo ->
        page.totalPages++
        page.movingObjects.movingObjects.add(mo.toMovingObjectSource)
        page
    }

private val InfoObject.toMovingObjectSource: MovingObjectSource
    get() = MovingObjectSource(
        name = name,
        code = code,
        city = "Olhão",
        size = size,
        color = color
    )

val MovingObject.toMovingObjectSource
    get() = MovingObjectSource(
        code = this.code,
        city = "Olhão",
        themeList = emptyList(),
        coordinates = CoordinateSource(
            x = this.x.toBigDecimal(),
            y = this.y.toBigDecimal()
        ),
        pointsOfSale = emptyList(),
        webCamSources = listOf(
            WebCamSource(
                coordinate = CoordinateSource(
                    x = this.x.toBigDecimal(),
                    y = this.y.toBigDecimal()
                ),
                wikiInfo = "",
                active = true,
                webCamImage = WebCamImageSource(
                    iconUrl = this.url,
                    thumbnailUrl = this.url,
                    previewUrl = this.url,
                    toenailUrlString = this.url
                )
            )
        )
    )

