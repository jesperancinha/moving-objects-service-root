package org.jesperancinha.moving.objects.domain

import org.jesperancinha.moving.objects.rest.*
import org.springframework.data.annotation.Id
import org.springframework.data.domain.PageRequest
import org.springframework.data.domain.Pageable
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
    @Column("name") val name: String,
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
    @Column("code") val code: String,
    @Column("size") val x: Int,
    @Column("color") val color: String
)

interface MovingObjectRepository : CoroutineCrudRepository<MovingObject, String> {
    suspend fun findByCode(code: String): MovingObject
    fun findAllBy(pageable: Pageable): Flux<MovingObject>
}

interface InfoObjectRepository : CoroutineCrudRepository<InfoObject, String>

@Service
class MovingObjectService(
    val movingObjectRepository: MovingObjectRepository
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
}

@Service
class InfoObjectService(
    val infoObjectRepository: InfoObjectRepository
) {
    fun getAll() = infoObjectRepository.findAll()
}

fun Flux<MovingObject>.toPage(pageSize: Int, pageOffSet: Int) =
    collectSortedList().map {
        Page(
            pageSize = pageSize,
            totalElements = it.size,
            pageNumber = pageOffSet,
            totalPages = 10000,
            movingObjects = MovingObjects(
                movingObjects = it.map { mo ->
                    MovingObjectSource(
                        code = mo.code,
                        name = mo.name,
                        city = "Olhão",
                        themeList = emptyList(),
                        coordinates = CoordinateSource(
                            x = mo.x.toBigDecimal(),
                            y = mo.y.toBigDecimal()
                        ),
                        pointsOfSale = emptyList(),
                        webCamSources = listOf(
                            WebCamSource(
                                mo.name,
                                coordinate = CoordinateSource(
                                    x = mo.x.toBigDecimal(),
                                    y = mo.y.toBigDecimal()
                                ),
                                wikiInfo = "",
                                active = true,
                                webCamImage = WebCamImageSource(
                                    iconUrl = mo.url,
                                    thumbnailUrl = mo.url,
                                    previewUrl = mo.url,
                                    toenailUrlString = mo.url
                                )
                            )
                        )
                    )
                }
            )
        )

    }
