package it.datalux.homeworktest.domain.usecase

import it.datalux.homeworktest.domain.PhotosRepository
import javax.inject.Inject

class PhotosUseCase @Inject constructor(
    private val photosRepository: PhotosRepository
) {
    suspend fun getPhotosList(reset: Boolean = false) = photosRepository.getPhotosList(reset)
}