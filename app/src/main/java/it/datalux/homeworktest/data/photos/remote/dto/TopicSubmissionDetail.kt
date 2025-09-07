package it.datalux.homeworktest.data.photos.remote.dto

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class TopicSubmission(
    @SerialName("status") val status: String? = null,
    @SerialName("approved_on") val approvedOn: String? = null
)