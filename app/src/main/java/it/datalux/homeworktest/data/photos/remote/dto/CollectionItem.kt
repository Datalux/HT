package it.datalux.homeworktest.data.photos.remote.dto

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class CollectionItem(
    @SerialName("id") val id: Int,
    @SerialName("title") val title: String? = null,
    @SerialName("published_at") val publishedAt: String? = null,
    @SerialName("last_collected_at") val lastCollectedAt: String? = null,
    @SerialName("updated_at") val updatedAt: String? = null,
    @SerialName("cover_photo") val coverPhoto: String? = null,
    @SerialName("user") val user: User? = null
)