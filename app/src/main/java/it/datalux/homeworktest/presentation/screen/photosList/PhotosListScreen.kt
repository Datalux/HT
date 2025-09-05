package it.datalux.homeworktest.presentation.screen.photosList

import android.annotation.SuppressLint
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.statusBarsPadding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.lazy.rememberLazyListState
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.derivedStateOf
import androidx.compose.runtime.getValue
import androidx.compose.runtime.remember
import androidx.compose.runtime.snapshotFlow
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import it.datalux.homeworktest.core.theme.background
import it.datalux.homeworktest.core.theme.titleStyle
import it.datalux.homeworktest.data.photos.mock.PhotosMockRepositoryImpl
import it.datalux.homeworktest.domain.entity.Photo
import it.datalux.homeworktest.domain.usecase.PhotosUseCase
import it.datalux.homeworktest.presentation.common.UIConstants
import it.datalux.homeworktest.presentation.common.bar.SearchBar
import it.datalux.homeworktest.presentation.components.card.PhotoItemCard
import it.datalux.homeworktest.presentation.components.loading.CenteredLoading
import kotlinx.coroutines.flow.distinctUntilChanged
import kotlinx.coroutines.flow.filter

@Composable
fun PhotoListScreen(
    photosListViewModel: PhotosViewModel = hiltViewModel(),
    onItemClick: (Photo) -> Unit = { },
) {
    PhotoListContent(photosListViewModel, onItemClick)
}

@Composable
fun PhotoListContent(
    photosListViewModel: PhotosViewModel,
    onItemClick: (Photo) -> Unit = { },
) {
    val photosList = photosListViewModel.photosList.distinctBy { it.id }
    val loading by photosListViewModel.loading.collectAsStateWithLifecycle()

    val listState = rememberLazyListState()

    val shouldLoadMore = remember {
        derivedStateOf {
            val totalItemsCount = listState.layoutInfo.totalItemsCount
            val lastVisibleItemIndex = listState.layoutInfo.visibleItemsInfo.lastOrNull()?.index ?: 0
            lastVisibleItemIndex >= (totalItemsCount - 2) && !loading

        }
    }

    LaunchedEffect(listState) {
        snapshotFlow { shouldLoadMore.value }
            .distinctUntilChanged()
            .filter { it }
            .collect {
                photosListViewModel.getPhotosList()
            }
    }

    Column(modifier = Modifier
            .fillMaxSize()
            .statusBarsPadding()
            .background(Color.Companion.background),
    ) {

        SearchBar(modifier = Modifier.padding(start = UIConstants.paddingSmall, top = UIConstants.paddingSmall, end = UIConstants.paddingSmall))

        if (photosList.isNotEmpty()) {
            LazyColumn(
                Modifier
                    .padding(all = UIConstants.paddingSmall),
                verticalArrangement = Arrangement.spacedBy(UIConstants.paddingSmall),
                state = listState
            ) {

                items(photosList, key = { it.id }) { item ->
                    PhotoItemCard(
                        photo = item,
                        onPhotoClicked = { onItemClick(item) }
                    )
                }

                if (loading) {
                    item {
                        CenteredLoading()
                    }
                }

            }
        } else {
            if(!loading) {
                Column(
                    modifier = Modifier.fillMaxSize(),
                    verticalArrangement = Arrangement.Center,
                    horizontalAlignment = Alignment.CenterHorizontally,
                ) {
                    Text(
                        text = "No results found.",
                        style = titleStyle
                    )
                }
            }
        }
    }
}

@SuppressLint("ViewModelConstructorInComposable")
@Composable
@Preview(showBackground = true)
fun PhotoListScreenPreview() {
    PhotoListScreen(
        PhotosViewModel(PhotosUseCase(PhotosMockRepositoryImpl()))
    )
}
