package it.datalux.homeworktest.data.common.mapper

import it.datalux.homeworktest.core.utils.toStringOrEmpty
import it.datalux.homeworktest.data.photos.remote.dto.Photo

fun Photo.toPhotoEntity() = it.datalux.homeworktest.domain.entity.Photo(
    this.id,
    photoUrl = this.urls.regular,
    userName = this.user.name,
    description = this.description.toStringOrEmpty(),
    userPhotoUrl = this.urls.small,
)