package it.datalux.homeworktest.data.photos.mock

import it.datalux.homeworktest.domain.PhotosRepository
import it.datalux.homeworktest.domain.entity.Photo
import it.datalux.homeworktest.domain.entity.photoMock
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow

class PhotosMockRepositoryImpl: PhotosRepository {

    override suspend fun getPhotosList(reset: Boolean): Flow<Result<List<Photo>>> {
        return flow {
            emit(Result.success(listOf(
                photoMock,
            )))
        }
    }

    override suspend fun search(
        query: String,
        reset: Boolean
    ): Flow<Result<List<Photo>>> {
        return flow {
            emit(Result.success(listOf(
                photoMock,
            )))
        }
    }
}