package it.datalux.homeworktest.data.photos.remote.dto

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class UserLinks(
    @SerialName("self") val self: String,
    @SerialName("html") val html: String,
    @SerialName("photos") val photos: String,
    @SerialName("likes") val likes: String,
    @SerialName("portfolio") val portfolio: String
)