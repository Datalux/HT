package it.datalux.homeworktest.data.photos.remote.dto

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class PhotoLinks(
    @SerialName("self") val self: String,
    @SerialName("html") val html: String,
    @SerialName("download") val download: String,
    @SerialName("download_location") val downloadLocation: String
)