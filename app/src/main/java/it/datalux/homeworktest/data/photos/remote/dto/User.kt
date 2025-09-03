package it.datalux.homeworktest.data.photos.remote.dto

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class User(
    @SerialName("id") val id: String,
    @SerialName("username") val username: String,
    @SerialName("name") val name: String,
    @SerialName("portfolio_url") val portfolioUrl: String?,
    @SerialName("bio") val bio: String?,
    @SerialName("location") val location: String?,
    @SerialName("total_likes") val totalLikes: Int,
    @SerialName("total_photos") val totalPhotos: Int,
    @SerialName("total_collections") val totalCollections: Int,
    @SerialName("instagram_username") val instagramUsername: String?,
    @SerialName("twitter_username") val twitterUsername: String?,
    @SerialName("profile_image") val profileImage: ProfileImage,
    @SerialName("links") val links: UserLinks
)