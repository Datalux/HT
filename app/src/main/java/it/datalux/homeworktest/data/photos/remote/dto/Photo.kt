package it.datalux.homeworktest.data.photos.remote.dto

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class Photo(
    @SerialName("id") val id: String,
    @SerialName("created_at") val createdAt: String,
    @SerialName("updated_at") val updatedAt: String,
    @SerialName("width") val width: Int,
    @SerialName("height") val height: Int,
    @SerialName("color") val color: String,
    @SerialName("blur_hash") val blurHash: String,
    @SerialName("likes") val likes: Int,
    @SerialName("liked_by_user") val likedByUser: Boolean,
    @SerialName("description") val description: String?,
    @SerialName("user") val user: User,
    @SerialName("current_user_collections") val currentUserCollections: List<Collection>,
    @SerialName("urls") val urls: Urls,
    @SerialName("links") val links: PhotoLinks
)