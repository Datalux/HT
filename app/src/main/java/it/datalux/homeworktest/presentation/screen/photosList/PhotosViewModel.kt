package it.datalux.homeworktest.presentation.screen.photosList

import android.util.Log
import androidx.compose.runtime.mutableStateListOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import it.datalux.homeworktest.domain.entity.Photo
import it.datalux.homeworktest.domain.usecase.PhotosUseCase
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


    init {
        getPhotosList()
    }

    fun getPhotosList(reset: Boolean = false) {
        _loading.value = true

        viewModelScope.launch {
            photosUseCase.getPhotosList(reset = reset)
                .catch { er ->
                    Log.e("PhotosListViewModel:getPhotosList", "$er")
                    _loading.value = false
                }
                .collect { result ->
                    val list = result.getOrDefault(emptyList())

                    if (reset) {
                        photosList.clear()
                    }

                    photosList.addAll(list)

                    _loading.value = false
                }
        }
    }
}