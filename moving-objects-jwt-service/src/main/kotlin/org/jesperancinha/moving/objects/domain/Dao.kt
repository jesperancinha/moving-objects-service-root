package org.jesperancinha.moving.objects.domain

import org.springframework.data.annotation.Id
import org.springframework.data.relational.core.mapping.Column
import org.springframework.data.relational.core.mapping.Table
import org.springframework.data.repository.kotlin.CoroutineCrudRepository
import org.springframework.stereotype.Service
import java.util.*

@Table
data class MovingObject(
    @Id val id: UUID? = null,
    @Column("name") val name: String,
    @Column("code") val code: String,
    @Column("folder") val folder: String,
    @Column("x") val x: Int,
    @Column("y") val y: Int
)

@Table
data class InfoObject(
    @Id val id: UUID? = null,
    @Column("code") val code: String,
    @Column("size") val x: Int,
    @Column("color") val y: Int
)

interface MovingObjectRepository : CoroutineCrudRepository<MovingObject, String>

interface InfoObjectRepository : CoroutineCrudRepository<InfoObject, String>

@Service
class MovingObjectService(
    val movingObjectRepository: MovingObjectRepository
) {

}

@Service
class InfoObjectService(
    val infoObjectRepository: InfoObjectRepository
) {

}