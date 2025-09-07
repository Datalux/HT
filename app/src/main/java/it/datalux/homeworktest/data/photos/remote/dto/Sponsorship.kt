package it.datalux.homeworktest.data.photos.remote.dto

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class Sponsorship(
    @SerialName("impression_urls") val impressionUrls: List<String>? = emptyList(),
    @SerialName("tagline") val tagline: String? = null,
    @SerialName("tagline_url") val taglineUrl: String? = null,
    @SerialName("sponsor") val sponsor: User? = null
)