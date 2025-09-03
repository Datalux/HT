package it.datalux.homeworktest.domain

import it.datalux.homeworktest.domain.entity.Photo
import kotlinx.coroutines.flow.Flow

interface PhotosRepository {

    suspend fun getPhotosList(
        reset: Boolean,
    ) : Flow<Result<List<Photo>>>
}