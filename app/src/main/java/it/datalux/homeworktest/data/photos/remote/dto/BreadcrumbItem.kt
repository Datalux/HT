package it.datalux.homeworktest.data.photos.remote.dto

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class BreadcrumbItem(
    @SerialName("slug") val slug: String? = null,
    @SerialName("title") val title: String? = null,
    @SerialName("index") val index: Int? = null,
    @SerialName("type") val type: String? = null
)