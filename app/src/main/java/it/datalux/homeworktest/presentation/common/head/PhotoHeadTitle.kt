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
import com.bumptech.glide.integration.compose.ExperimentalGlideComposeApi
import com.bumptech.glide.integration.compose.GlideImage
import com.bumptech.glide.integration.compose.placeholder
import it.datalux.homeworktest.R
import it.datalux.homeworktest.core.theme.subtitleStyle
import it.datalux.homeworktest.core.theme.titleStyle
import it.datalux.homeworktest.domain.entity.Photo
import it.datalux.homeworktest.domain.entity.photoMock
import it.datalux.homeworktest.presentation.common.UIConstants

@OptIn(ExperimentalGlideComposeApi::class)
@Composable
fun PhotoHeadTitle(
    modifier: Modifier = Modifier,
    photo: Photo,
    maxLines: Int = Int.MAX_VALUE
    ) {

    Row(
        modifier = Modifier.padding(start = UIConstants.paddingLarge, top = UIConstants.paddingSmall, end = UIConstants.paddingLarge, bottom = UIConstants.paddingSmall),
        horizontalArrangement = Arrangement.spacedBy(UIConstants.paddingSmall)
    ) {
        GlideImage(
            modifier = modifier.size(UIConstants.userPhotoIconSize).clip(CircleShape),
            contentScale = ContentScale.Crop,
            model = photo.userPhotoUrl,
            contentDescription = "Profile user photo",
            loading = placeholder(R.drawable.thumb_icon),
            failure = placeholder(R.drawable.thumb_icon)

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
                maxLines = maxLines,
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