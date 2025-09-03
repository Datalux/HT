package it.datalux.homeworktest.data.photos.repository

import it.datalux.homeworktest.core.utils.Paginator
import it.datalux.homeworktest.data.common.mapper.toPhotoEntity
import it.datalux.homeworktest.data.photos.remote.api.PhotosApi
import it.datalux.homeworktest.domain.PhotosRepository
import it.datalux.homeworktest.domain.entity.Photo
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import javax.inject.Inject

class PhotosRepositoryImpl @Inject constructor(
    private val api: PhotosApi
): PhotosRepository {

    val MAX_PAGE_SIZE = 30

    private val paginator = Paginator()

    override suspend fun getPhotosList(
        reset: Boolean,
    ): Flow<Result<List<Photo>>> {
        if (reset) paginator.reset()

        return flow {
            val response = api.getPhotosList(
                page = paginator.nextPage,
                perPage = MAX_PAGE_SIZE
            )
            if (response.isSuccessful) {
                response.body()?.let { body ->
                    emit(Result.success(body.map { it.toPhotoEntity() }))
                }
            } else {
                paginator.previousPage
                emit(Result.failure(Exception()))
            }
        }
    }


}