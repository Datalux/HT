package it.datalux.homeworktest.domain.entity

import kotlinx.serialization.Serializable
import java.util.UUID

@Serializable
data class Photo(
    val id: String,
)

val photoMock = Photo(
    id = UUID.randomUUID().toString()
)