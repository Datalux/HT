package it.datalux.homeworktest.data.photos.remote.dto

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class AlternativeSlugs(
    @SerialName("en") val en: String? = null,
    @SerialName("es") val es: String? = null,
    @SerialName("ja") val ja: String? = null,
    @SerialName("fr") val fr: String? = null,
    @SerialName("it") val it: String? = null,
    @SerialName("ko") val ko: String? = null,
    @SerialName("de") val de: String? = null,
    @SerialName("pt") val pt: String? = null,
    @SerialName("id") val id: String? = null
)