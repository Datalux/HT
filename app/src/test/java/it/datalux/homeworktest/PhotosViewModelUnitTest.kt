package it.datalux.homeworktest

import it.datalux.homeworktest.data.photos.mock.PhotosMockRepositoryImpl
import it.datalux.homeworktest.domain.PhotosRepository
import it.datalux.homeworktest.domain.usecase.PhotosUseCase
import it.datalux.homeworktest.presentation.screen.photosList.PhotosViewModel
import kotlinx.coroutines.delay
import kotlinx.coroutines.test.runTest
import org.junit.Assert.assertEquals
import org.junit.Assert.assertNotNull
import org.junit.Assert.assertNull
import org.junit.Assert.assertTrue
import org.junit.Before
import org.junit.Test
import org.mockito.Mock

/**
 * Example local unit test, which will execute on the development machine (host).
 *
 * See [testing documentation](http://d.android.com/tools/testing).
 */
class PhotosViewModelUnitTest {

    @Mock
    private lateinit var photosRepository: PhotosRepository
    private lateinit var photosUseCase: PhotosUseCase

    private lateinit var photosViewModel: PhotosViewModel


    @Before
    fun setup(){
        photosRepository = PhotosMockRepositoryImpl()
        photosUseCase = PhotosUseCase(photosRepository)
        photosViewModel = PhotosViewModel(photosUseCase,)
    }

    @Test
    fun load_photos() = runTest {
        photosViewModel.photosList.clear()

        assertEquals(photosViewModel.photosList.isEmpty(), true)

        photosViewModel.loadPhotos()
        delay(1000)

        assertEquals(photosViewModel.photosList.isNotEmpty(), true)
    }

    @Test
    fun search_photos() = runTest {
        photosViewModel.photosList.clear()

        photosViewModel.loadPhotos()
        delay(1000)

        val initialListSize = photosViewModel.photosList.size
        assertTrue(initialListSize > 0)

        val searchQuery = "cat"
        photosViewModel.loadPhotos(query = searchQuery)
        delay(1000)

        assertTrue(photosViewModel.photosList.isNotEmpty())
        assertTrue(photosViewModel.photosList.all { it.description.contains(searchQuery, ignoreCase = true) })
    }
}