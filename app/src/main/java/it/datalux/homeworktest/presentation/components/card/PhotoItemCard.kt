package it.datalux.homeworktest.presentation.components.card

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.tooling.preview.Preview
import com.bumptech.glide.integration.compose.ExperimentalGlideComposeApi
import com.bumptech.glide.integration.compose.GlideImage
import it.datalux.homeworktest.core.theme.backgroundSecondary
import it.datalux.homeworktest.core.utils.debounceClickable
import it.datalux.homeworktest.domain.entity.Photo
import it.datalux.homeworktest.domain.entity.photoMock
import it.datalux.homeworktest.presentation.common.UIConstants
import it.datalux.homeworktest.presentation.common.head.PhotoHeadTitle

@OptIn(ExperimentalGlideComposeApi::class)
@Composable
fun PhotoItemCard(
    modifier: Modifier = Modifier,
    photo: Photo,
    onPhotoClicked: () -> Unit = { }
) {
    Card(
        colors = CardDefaults.cardColors(
            containerColor = Color.backgroundSecondary,
        ),
        modifier = modifier.fillMaxWidth(),
        shape = RoundedCornerShape(size = UIConstants.radiusSmall)
    ) {
        Column {
            GlideImage(
                modifier = modifier.debounceClickable { onPhotoClicked() },
                model = photo.photoUrl,
                contentScale = ContentScale.FillWidth,
                contentDescription = "null",
            )

            PhotoHeadTitle(photo = photo, maxLines = 1)
        }
    }
}

@Preview
@Composable
fun PhotoItemCardPreview() {
    PhotoItemCard(photo = photoMock)
}