package it.datalux.homeworktest.presentation.common.head

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.bumptech.glide.integration.compose.ExperimentalGlideComposeApi
import com.bumptech.glide.integration.compose.GlideImage
import it.datalux.homeworktest.core.theme.subtitleStyle
import it.datalux.homeworktest.core.theme.titleStyle
import it.datalux.homeworktest.domain.entity.Photo
import it.datalux.homeworktest.domain.entity.photoMock

@OptIn(ExperimentalGlideComposeApi::class)
@Composable
fun PhotoHeadTitle(
    modifier: Modifier = Modifier,
    photo: Photo,
    maxLines: Int = Int.MAX_VALUE
    ) {

    Row(
        modifier = Modifier.padding(start = 16.dp, top = 10.dp, end = 16.dp, bottom = 10.dp),
        horizontalArrangement = Arrangement.spacedBy(10.dp)
    ) {
        GlideImage(
            modifier = modifier.size(48.dp).clip(CircleShape),
            contentScale = ContentScale.Crop,
            model = photo.userPhotoUrl,
            contentDescription = photo.description
        )

        Column(
            verticalArrangement = Arrangement.Center
        ) {
            Text(
                text = photo.userName,
                style = titleStyle
            )

            Text(
                text = photo.description,
                style = subtitleStyle,
                maxLines = 1,
                overflow = TextOverflow.Ellipsis,
            )
        }
    }
}

@Preview
@Composable
fun PhotoHeadTitlePreview() {
    PhotoHeadTitle(
        photo = photoMock,
        maxLines = 1
    )
}