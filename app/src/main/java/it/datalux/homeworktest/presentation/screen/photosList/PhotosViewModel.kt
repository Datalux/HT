package it.datalux.homeworktest.presentation.screen.photosList

import android.util.Log
import androidx.compose.runtime.mutableStateListOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import it.datalux.homeworktest.domain.entity.Photo
import it.datalux.homeworktest.domain.usecase.PhotosUseCase
import kotlinx.coroutines.Job
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
open class PhotosViewModel @Inject constructor(
    private val photosUseCase: PhotosUseCase,
): ViewModel() {

    var photosList = mutableStateListOf<Photo>()
        private set

    private var _loading = MutableStateFlow(false)
    val loading = _loading.asStateFlow()


    private var _searchMode = MutableStateFlow(false)
    val searchMode = _searchMode.asStateFlow()

    private var currentQuery: String? = null
    private var fetchJob: Job? = null

    init {
        loadPhotos()
    }

    fun loadPhotos(query: String? = null, loadMore: Boolean = false) {
        fetchJob?.cancel()

        _searchMode.value = query != null

        val isNewSearchOrInitialLoad = !loadMore
        val queryToUse = query?.trim()

        if (isNewSearchOrInitialLoad) {
            currentQuery = queryToUse
        }

        _loading.value = true

        fetchJob = viewModelScope.launch {
            val flowResult = if (currentQuery.isNullOrEmpty()) {
                photosUseCase.getPhotosList(reset = isNewSearchOrInitialLoad)
            } else {
                photosUseCase.search(query = currentQuery!!, reset = isNewSearchOrInitialLoad)
            }

            flowResult
                .catch { er ->
                    Log.e("PhotosViewModel:loadPhotos", "Error loading photos (query: $currentQuery, loadMore: $loadMore): $er")
                    _loading.value = false
                }
                .collect { result ->
                    val newList = result.getOrDefault(emptyList())

                    if (isNewSearchOrInitialLoad) {
                        photosList.clear()
                    }

                    val existingIds = photosList.map { it.id }.toSet()
                    photosList.addAll(newList.filterNot { existingIds.contains(it.id) })

                    _loading.value = false
                }
        }
    }


    fun onBackButtonClick() {
        if (currentQuery != null) {
            currentQuery = null
            _searchMode.value = false
            loadPhotos()
        }
    }
}