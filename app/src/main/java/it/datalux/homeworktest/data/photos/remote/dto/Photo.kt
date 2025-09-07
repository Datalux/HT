package it.datalux.homeworktest.data.photos.remote.dto

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class Photo(
    @SerialName("id") val id: String,
    @SerialName("slug") val slug: String? = null,
    @SerialName("alternative_slugs") val alternativeSlugs: AlternativeSlugs? = null,
    @SerialName("created_at") val createdAt: String,
    @SerialName("updated_at") val updatedAt: String,
    @SerialName("promoted_at") val promotedAt: String? = null,
    @SerialName("width") val width: Int,
    @SerialName("height") val height: Int,
    @SerialName("color") val color: String? = null,
    @SerialName("blur_hash") val blurHash: String? = null,
    @SerialName("description") val description: String? = null,
    @SerialName("alt_description") val altDescription: String? = null,
    @SerialName("breadcrumbs") val breadcrumbs: List<BreadcrumbItem> = emptyList(),
    @SerialName("urls") val urls: Urls,
    @SerialName("links") val links: PhotoLinks,
    @SerialName("likes") val likes: Int,
    @SerialName("liked_by_user") val likedByUser: Boolean,
    @SerialName("current_user_collections") val currentUserCollections: List<CollectionItem> = emptyList(),
    @SerialName("sponsorship") val sponsorship: Sponsorship? = null,
    @SerialName("topic_submissions") val topicSubmissions: Map<String, TopicSubmission> = emptyMap(),
    @SerialName("asset_type") val assetType: String? = null,
    @SerialName("user") val user: User
)