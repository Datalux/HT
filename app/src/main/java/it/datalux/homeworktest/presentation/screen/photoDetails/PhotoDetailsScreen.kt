package it.datalux.homeworktest.presentation.screen.photoDetails

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.aspectRatio
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.statusBarsPadding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import com.bumptech.glide.integration.compose.ExperimentalGlideComposeApi
import com.bumptech.glide.integration.compose.GlideImage
import it.datalux.homeworktest.R
import it.datalux.homeworktest.core.theme.background
import it.datalux.homeworktest.core.utils.debounceClickable
import it.datalux.homeworktest.domain.entity.Photo
import it.datalux.homeworktest.domain.entity.photoMock
import it.datalux.homeworktest.presentation.common.UIConstants
import it.datalux.homeworktest.presentation.common.head.PhotoHeadTitle

@OptIn(ExperimentalGlideComposeApi::class)
@Composable
fun PhotoDetailsScreen(
    modifier: Modifier = Modifier,
    photo: Photo,
    onBackClick: () -> Unit = { }
) {

    val aspectRatio = photo.width.toFloat() / photo.height.toFloat()

    Column(
        modifier = modifier
            .fillMaxSize()
            .background(Color.Companion.background)
            .verticalScroll(rememberScrollState())
    ) {
        Box {
            GlideImage(
                modifier = Modifier.aspectRatio(aspectRatio),
                model = photo.photoUrl,
                contentScale = ContentScale.FillWidth,
                contentDescription = photo.description,
            )

            Image(
                painter = painterResource(R.drawable.icon_arrow_back),
                contentDescription = null,
                modifier = modifier
                    .statusBarsPadding()
                    .padding(start = UIConstants.horizontalPadding, end = UIConstants.horizontalPadding)
                    .align(Alignment.TopStart)
                    .size(UIConstants.iconSize)
                    .debounceClickable { onBackClick() },
            )
        }

        PhotoHeadTitle(photo = photo)
    }
}

@Composable
@Preview
fun PhotoDetailScreenPreview() {
    PhotoDetailsScreen(photo = photoMock, onBackClick = { })
}