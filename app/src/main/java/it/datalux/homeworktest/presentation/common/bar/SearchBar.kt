package it.datalux.homeworktest.presentation.common.bar

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.input.KeyboardCapitalization
import androidx.compose.ui.tooling.preview.Preview
import it.datalux.homeworktest.R
import it.datalux.homeworktest.core.theme.backgroundSecondary
import it.datalux.homeworktest.core.theme.ctaStyle
import it.datalux.homeworktest.core.theme.onBackgroundSecondary
import it.datalux.homeworktest.core.utils.debounceClickable
import it.datalux.homeworktest.presentation.common.UIConstants


@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun SearchBar(
    modifier: Modifier = Modifier,
    onSearch: (String) -> Unit = { }
) {
    val (text, onTextChange) = remember { mutableStateOf("") }

    Card(
        modifier = modifier.fillMaxWidth(),
        colors = CardDefaults.cardColors(
            containerColor = Color.backgroundSecondary,
        ),
        shape = RoundedCornerShape(size = UIConstants.radiusLarge)
    ) {
        Row(
            modifier = modifier
                .fillMaxWidth(),
            verticalAlignment = Alignment.CenterVertically
        ) {
            TextField(
                value = text,
                onValueChange = onTextChange,
                modifier = Modifier.weight(1f),
                placeholder = { Text("Search", style = ctaStyle) },
                textStyle = ctaStyle,
                maxLines = 1,
                colors = TextFieldDefaults.colors(
                    unfocusedContainerColor = Color.Transparent,
                    focusedContainerColor = Color.Transparent,
                    disabledContainerColor = Color.Transparent,
                    focusedIndicatorColor = Color.Transparent,
                    unfocusedIndicatorColor = Color.Transparent,
                    disabledIndicatorColor = Color.Transparent,
                    cursorColor = Color.onBackgroundSecondary,
                ),

                keyboardOptions = KeyboardOptions(
                    imeAction = ImeAction.Search,
                    capitalization = KeyboardCapitalization.Sentences
                ),
                keyboardActions = KeyboardActions(
                    onSearch = {
                        onSearch(text.trim())
                    }
                )
            )

            Image(
                painter = painterResource(R.drawable.icon_search),
                contentDescription = "Search",
                modifier = Modifier
                    .padding(end = UIConstants.paddingLarge)
                    .size(UIConstants.iconSize)
                    .debounceClickable {
                        onSearch(text)
                    }
            )
        }
    }
}

@Composable
@Preview
fun SearchBarPreview() {
    SearchBar()
}

