package it.datalux.homeworktest.data.photos.remote.dto

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class ProfileImage(
    @SerialName("small") val small: String? = null,
    @SerialName("medium") val medium: String? = null,
    @SerialName("large") val large: String? = null
)