package it.datalux.homeworktest.data.photos.remote.dto

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class UserLinks(
    @SerialName("self") val self: String? = null,
    @SerialName("html") val html: String? = null,
    @SerialName("photos") val photos: String? = null,
    @SerialName("likes") val likes: String? = null,
    @SerialName("portfolio") val portfolio: String? = null
)