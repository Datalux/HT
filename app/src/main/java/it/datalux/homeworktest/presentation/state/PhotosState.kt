package it.datalux.homeworktest.presentation.state

import it.datalux.homeworktest.domain.entity.Photo

data class PhotosState(
    val photos: List<Photo> = emptyList(),
    val currentPage: Int = 1,
    val canLoadMore: Boolean = true,
    val isLoading: Boolean = false,
    val currentQuery: String = ""
)