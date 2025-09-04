package it.datalux.homeworktest.domain.entity

import kotlinx.serialization.Serializable
import java.util.UUID

@Serializable
data class Photo(
    val id: String,
    val photoUrl: String,
    val userName: String,
    val description: String,
    val userPhotoUrl: String
)

val photoMock = Photo(
    id = UUID.randomUUID().toString(),
    photoUrl = "https://images.unsplash.com/photo-1756660908471-a4443ca3703b?crop=entropy\\u0026cs=tinysrgb\\u0026fit=max\\u0026fm=jpg\\u0026ixid=M3w3OTk5NjB8MHwxfGFsbHw1fHx8fHx8fHwxNzU2OTc4MzA2fA\\u0026ixlib=rb-4.1.0\\u0026q=80\\u0026w=1080",
    userName = "Nathan Anderson",
    description = "This is a cat for sure",
    userPhotoUrl = "",
)