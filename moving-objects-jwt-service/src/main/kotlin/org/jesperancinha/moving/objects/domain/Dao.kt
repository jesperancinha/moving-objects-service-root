package org.jesperancinha.moving.objects.domain

import org.springframework.data.annotation.Id
import org.springframework.data.relational.core.mapping.Column
import org.springframework.data.relational.core.mapping.Table
import org.springframework.data.repository.kotlin.CoroutineCrudRepository
import org.springframework.stereotype.Service
import java.time.LocalDateTime
import java.util.*
import kotlin.io.path.absolute
import kotlin.io.path.toPath

@Table
data class MovingObject(
    @Id val id: UUID? = null,
    @Column("name") val name: String,
    @Column("code") val code: String,
    @Column("folder") val folder: String,
    @Column("url") val url: String,
    @Column("x") val x: Int,
    @Column("y") val y: Int
)

@Table
data class InfoObject(
    @Id val id: UUID? = null,
    @Column("code") val code: String,
    @Column("size") val x: Int,
    @Column("color") val color: String
)

interface MovingObjectRepository : CoroutineCrudRepository<MovingObject, String> {
    suspend fun findByCode(code: String): MovingObject
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
                    .let {
                        val root = javaClass.getResource("/")?.toURI()?.toPath()
                        val allImages =
                            javaClass.getResource(it)?.toURI()?.toPath()?.filter { file -> file.endsWith("jpg") }
                        val countImages = allImages?.size ?: 0
                        val delta = 60 / countImages
                        val currentMinute = LocalDateTime.now().minute
                        val index = currentMinute / delta
                        root?.let { it1 -> allImages?.get(index)?.relativize(it1) }
                    }
            }
}

@Service
class InfoObjectService(
    val infoObjectRepository: InfoObjectRepository
) {
    fun getAll() = infoObjectRepository.findAll()
}