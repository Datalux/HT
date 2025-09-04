package it.datalux.homeworktest.presentation.components.card

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.bumptech.glide.integration.compose.ExperimentalGlideComposeApi
import com.bumptech.glide.integration.compose.GlideImage
import it.datalux.homeworktest.core.theme.backgroundSecondary
import it.datalux.homeworktest.core.theme.subtitleStyle
import it.datalux.homeworktest.core.theme.titleStyle
import it.datalux.homeworktest.domain.entity.Photo
import it.datalux.homeworktest.domain.entity.photoMock

@OptIn(ExperimentalGlideComposeApi::class)
@Composable
fun PhotoItemCard(
    modifier: Modifier = Modifier,
    photo: Photo
) {
    Card(
        colors = CardDefaults.cardColors(
            containerColor = Color.backgroundSecondary,
        ),
        modifier = modifier.fillMaxWidth(),
        shape = RoundedCornerShape(size = 8.dp)
    ) {
        Column {
            GlideImage(
                model = photo.photoUrl,
                contentScale = ContentScale.FillWidth,
                contentDescription = "null",

            )

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
    }
}

@Preview
@Composable
fun PhotoItemCardPreview() {
    PhotoItemCard(photo = photoMock)
}