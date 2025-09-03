package it.datalux.homeworktest.data.photos.remote.dto

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class Collection(
    @SerialName("id") val id: Int,
    @SerialName("title") val title: String,
    @SerialName("published_at") val publishedAt: String,
    @SerialName("last_collected_at") val lastCollectedAt: String,
    @SerialName("updated_at") val updatedAt: String,
    @SerialName("cover_photo") val coverPhoto: String?,
    @SerialName("user") val user: String?
)