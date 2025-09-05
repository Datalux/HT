package it.datalux.homeworktest.core.theme

import androidx.compose.runtime.Composable
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.Font
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.sp
import it.datalux.homeworktest.R

val titleStyle: TextStyle
    @Composable
    get() {
        return TextStyle(
            fontFamily = FontFamily(Font(R.font.roboto_medium)),
            fontWeight = FontWeight.Medium,
            fontSize = 20.sp,
            color = Color.onBackground
        )
    }

val subtitleStyle: TextStyle
    @Composable
    get() {
        return TextStyle(
            fontFamily = FontFamily(Font(R.font.roboto_regular)),
            fontWeight = FontWeight.Normal,
            fontSize = 20.sp,
            color = Color.onBackground
        )
    }

val ctaStyle: TextStyle
    @Composable
    get() {
        return TextStyle(
            fontFamily = FontFamily(Font(R.font.roboto_regular)),
            fontWeight = FontWeight.Normal,
            fontSize = 16.sp,
            color = Color.onBackgroundSecondary
        )
    }